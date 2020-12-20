package gameClient;

import Server.Game_Server_Ex2;
import api.*;
import gameClient.util.Gframe;
import gameClient.util.PanelOpening;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.List;

import static java.lang.Thread.*;

/**
 * this class initiate and run the Pokemon's game
 */
public class Ex2_Client implements Runnable {
    private static Gframe _win;
    private static Arena _ar;
    private static HashMap<Object, List<node_data>> pokemonList;
    private static DWGraph_Algo gg;
    private static HashMap<Integer, CL_Pokemon> currFruit;
    private static PanelOpening panelOpening;

    /***
     * this main run the pokemon game
     */

    public static void main(String[] a) throws InterruptedException {
        panelOpening = new PanelOpening();

    }

    /**
     * this method is constructor for this class
     */
    public Ex2_Client() {
        pokemonList = new HashMap<Object, List<node_data>>();
        gg = new DWGraph_Algo();
        currFruit = new HashMap<Integer, CL_Pokemon>();
    }

    /**
     * this method initiate and run the game and call the method moveAgent that choose
     * where the agent will move on the graph
     */
    @Override
    public void run() {
        game_service game = Game_Server_Ex2.getServer(panelOpening.getScenario_num()); // you have [0,23] games
        //  game.login(panelOpening.getID());
        String g = game.getGraph();
        String pks = game.getPokemons();
        gg.load2(game.getGraph());
        init(game);
        game.startGame();
        _win.setTitle("level :" + panelOpening.getScenario_num());
        while (game.isRunning()) {
            moveAgants(game);
            _win.repaint();

        }
        String res = game.toString();
        System.out.println(res);
        System.exit(0);
    }

    /**
     * this method build a Pokemon's list and agent list
     * and search  the shortest path to pokemon from
     * each agent to pokemon by using the method nextNode
     * and go all over the path and ear the pokemon
     *
     * @param game
     */
    private static void moveAgants(game_service game) {
        String lg = game.getAgents();
        List<CL_Agent> agentList = Arena.getAgents(lg, gg.getGraph());
        _ar.setAgents(agentList);
        _win.repaint();
        String fs = game.getPokemons();
        List<CL_Pokemon> ffs = Arena.json2Pokemons(fs);
        _ar.setPokemons(ffs);
        for (CL_Pokemon pokemon : _ar.getPokemons()) {
            _ar.updateEdge(pokemon, gg.getGraph());
        }
        _win.repaint();
        List<CL_Agent> tempList = new ArrayList<>();
        long minTime = Integer.MAX_VALUE;
        for (int i = 0; i < agentList.size(); i++) {
            CL_Agent ag = agentList.get(i);
            int id = ag.getID();
            int dest = ag.getNextNode();
            int src = ag.getSrcNode();
            double v = ag.getValue();
            if (dest == -1) {
                dest = nextNode(src, ag, game);
                ag.set_curr_fruit(currFruit.get(ag.getID()));
                ag.set_curr_edge(gg.getGraph().getEdge(src, dest));
                if (ag.get_curr_fruit() != null && ag.get_curr_fruit() != null)
                    ag.set_SDT(0);
                game.chooseNextEdge(ag.getID(), dest);
                if (ag.get_curr_fruit() != null && ag.get_curr_fruit().get_edge().equals(ag.get_curr_edge())) {
                    try {
                        sleep(ag.get_sg_dt());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    game.move();
                    _ar.setTime(game.timeToEnd());
                    _win.repaint();


                } else {
                    tempList.add(ag);
                }


            } else {
                ag.set_curr_edge(gg.getGraph().getEdge(ag.getSrcNode(), ag.getNextNode()));
                ag.set_curr_fruit(currFruit.get(ag.getID()));
                if (ag.get_curr_fruit() != null && ag.get_curr_fruit() != null)
                    ag.set_SDT(0);
                if (ag.get_curr_fruit() != null && (ag.get_curr_fruit().get_edge().equals(ag.get_curr_edge()))) {
                    try {
                        sleep(ag.get_sg_dt());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    game.move();
                    _ar.setTime(game.timeToEnd());
                    _win.repaint();
                } else {
                    tempList.add(ag);
                }

            }

        }

        for (CL_Agent agent : agentList) {
            if (agent.get_sg_dt() < minTime && tempList.contains(agent)) {
                minTime = agent.get_sg_dt();
            } else if (agent.getDisTime() < minTime && !tempList.contains(agent) && agent.getDisTime() > 0) {
                minTime = agent.getDisTime();
            }

        }
        if (minTime == Integer.MAX_VALUE)
            minTime = 0;
        try {
            sleep(minTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.move();
        _ar.setTime(game.timeToEnd());
        _win.repaint();
    }


    /**
     * this method get a src of the agent agent and search the shortest
     * to pokemon and return the next dest the he need to go
     *
     * @param src->src agent
     * @param ag       ->agent
     * @param game
     * @return dest of the node
     */
    private static int nextNode(int src, CL_Agent ag, game_service game) {
        double shortestPath = Integer.MAX_VALUE;
        CL_Pokemon maxPokemon = new CL_Pokemon(null, 0, 0, null);
        if (pokemonList.get(ag.getID()) == null || pokemonList.get(ag.getID()).size() == 0) {
            for (CL_Pokemon pokemon : _ar.getPokemons()) {
                boolean flag = true;
                for (CL_Pokemon pokemon1 : currFruit.values()) {
                    if (pokemon.equals(pokemon1)) {
                        flag = false;
                        break;
                    }

                }
                if (flag) {
                    double s2 = (gg.shortestPathDist(src, pokemon.get_edge().getSrc()) + pokemon.get_edge().getWeight());
                    if (s2 < shortestPath) {
                        shortestPath = s2;
                        maxPokemon = pokemon;
                    }
                }


            }
            if (maxPokemon.get_edge() == null)
                return src;

            List<node_data> list = new ArrayList<node_data>();
            list = gg.shortestPath(src, maxPokemon.get_edge().getSrc());
            list.add(gg.getGraph().getNode(maxPokemon.get_edge().getDest()));
            pokemonList.put(ag.getID(), list);
            currFruit.put(ag.getID(), maxPokemon);

        }

        return pokemonList.get(ag.getID()).remove(0).getKey();


    }

    /**
     * this method initiate the game and open the frame
     * and place the agent close to the Pokemon's
     *
     * @param game
     */
    private void init(game_service game) {
        String g = game.getGraph();
        String fs = game.getPokemons();
        directed_weighted_graph gg1 = gg.getGraph();
        _ar = new Arena();
        _ar.setGraph(gg1);
        _ar.setPokemons(Arena.json2Pokemons(fs));
        _win = new Gframe("test Ex2");
        _win.update(_ar);
        String info = game.toString();
        JSONObject line;
        try {
            line = new JSONObject(info);
            JSONObject gamejson = line.getJSONObject("GameServer");
            int numOfAgents = gamejson.getInt("agents");
            System.out.println(info);
            System.out.println(game.getPokemons());
            ArrayList<CL_Pokemon> pokemons = Arena.json2Pokemons(game.getPokemons());
            for (int a = 0; a < pokemons.size(); a++) {
                Arena.updateEdge(pokemons.get(a), gg1);
            }
            List<List<node_data>> componentList = gg.searchComponents();
            if (componentList.size() == 1)
                setOnGraph1(game, pokemons, numOfAgents, gg.getGraph());
            else {
                setOnGraph2(game, numOfAgents, componentList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * if the graph has one connectivity component then this method place the agents close to the Pokemon's
     * with the highest value
     *
     * @param game
     * @param g
     * @param numOfAgents
     * @param pokemons
     */
    public void setOnGraph1(game_service game, List<CL_Pokemon> pokemons, int numOfAgents, directed_weighted_graph g) {
        int numOfPok = pokemons.size();
        List<CL_Pokemon> countrList = new ArrayList<CL_Pokemon>();
        while (numOfPok != 0) {
            CL_Pokemon tempPok = new CL_Pokemon(null, 0, 0, null);
            for (CL_Pokemon pokemon : pokemons) {
                if (pokemon.getValue() > tempPok.getValue() && !countrList.contains(pokemon))
                    tempPok = pokemon;
            }
            countrList.add(tempPok);
            if (tempPok.getLocation() == null || !game.addAgent(tempPok.get_edge().getSrc()))
                break;
            numOfAgents--;
            numOfPok--;
        }
        if (numOfAgents > 0) {
            while (numOfAgents != 0) {
                Iterator<node_data> n = g.getV().iterator();
                node_data nNext = n.next();
                game.addAgent(nNext.getKey());
                numOfAgents--;
            }
        }

    }

    /**
     * if there is more than one connectivity components the we insert to this method and we place in each
     * connectivity components agents
     *
     * @param game
     * @param components
     * @param numOfAgent
     */
    public void setOnGraph2(game_service game, int numOfAgent, List<List<node_data>> components) {


        while (numOfAgent > 0) {
            for (List<node_data> list : components) {
                game.addAgent(list.get(0).getKey());
                numOfAgent--;
            }
        }
    }

}
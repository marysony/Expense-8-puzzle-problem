import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
// This is a 8 puzzle problem code in java 


// this is a code for 8 puzzle problem.
public class Expensive_8_puzzle {
    
 // This is my  code
	 public static Integer[][] startState;
     public static Integer[][] goalState;
     public static String method;
     public static boolean dump_flag;
     public static void main(String[] args) {
        
        
        // Check for correct number of arguments
        if (args.length < 2) {
            System.out.println("Usage: expense_8_puzzle <start-file> <goal-file> [<method>] [<dump-flag>]");
            return;
        }

        // Parse the command line arguments
        String startFile = args[0];
        String goalFile = args[1];
        if (args.length >= 3) 
        {
            method=args[2];
        }
         else {
            method = "a*";
        }
        if (args.length >= 4) {
            dump_flag = Boolean.parseBoolean(args[3]);
        } else {
            dump_flag = false;
        }
        Integer[][] startState=new Integer[3][3];
        Integer[][] goalState=new Integer[3][3];
        try (BufferedReader br = new BufferedReader(new FileReader(startFile))) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null) {
                if (line.equals("END OF FILE")) {
                    break;
                }

                String[] nums = line.split(" ");

                for (int col = 0; col < 3; col++) {
                    startState[row][col] = Integer.parseInt(nums[col]);
                }

                row++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(goalFile))) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null) {
                if (line.equals("END OF FILE")) {
                    break;
                }

                String[] nums = line.split(" ");

                for (int col = 0; col < 3; col++) {
                    goalState[row][col] = Integer.parseInt(nums[col]);
                }

                row++;
            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Please wait For the program to run- It may take a while");
       // System.out.println("Valid search methods \n options - bfs,dfs,ucs,dls,ids,astar,greedy");
        // Read in the start and goal files
        boolean success=false;
        BFS bfs;
        DLS dls;
        UCS ucs;
        Greedy gdy;
        Astar ast;
        DFS dfs;
        DLS ids;
        // Determine which search method to use
        switch (method) {
            case "bfs":
            bfs = new BFS(startState,goalState);
            success = bfs.solveBFS(dump_flag);
            if(success)
		{			
			System.out.println("Nodes expanded: "+bfs.getExploredNodes().size());
			System.out.println("Search depth: " + bfs.getCurrentNode().getSearchDepth());
			System.out.println("Max Search depth: " + bfs.getCurrentNode().getMaxSearchDepth());
			System.out.println("Path to goal: " + bfs.getCurrentNode().getPathToGoal());
			
			System.out.println("Cost to goal: " + bfs.getCurrentNode().getCostOfPath());
		}
		else
		{
			System.err.println("no solution found!");
		}
                break;
                case "dfs":
                dfs = new DFS(startState,goalState);
                success = dfs.solveDFS(dump_flag);
                if(success)
            {			
                System.out.println("Nodes expanded: "+dfs.getExploredNodes().size());
                System.out.println("Search depth: " + dfs.getCurrentNode().getSearchDepth());
                System.out.println("Max Search depth: " + dfs.getCurrentNode().getMaxSearchDepth());
                System.out.println("Path to goal: " + dfs.getCurrentNode().getPathToGoal());
                
                System.out.println("Cost to goal: " + dfs.getCurrentNode().getCostOfPath());
            }
            else
            {
                System.err.println("no solution found!");
            }
                    break;
                
            case "dls":
            dls = new DLS(startState,goalState);
            int dl;
            
            System.out.println("Enter Depth Limit");
            Scanner in=new Scanner(System.in);
            dl=in.nextInt();
		    success = dls.solveDLS(dl,dump_flag);
            if(success)
		{
			
			System.out.println("Nodes expanded: "+dls.getExploredNodes().size());
			System.out.println("Search depth: " + dls.getCurrentNode().getSearchDepth());
			System.out.println("Max Search depth: " + dls.getCurrentNode().getMaxSearchDepth());
			System.out.println("Path to goal: " + dls.getCurrentNode().getPathToGoal());
			
			System.out.println("Cost to goal: " + dls.getCurrentNode().getCostOfPath());
		}
		else
		{
			System.err.println("no solution found!");
		}
            break;
            case "ids":
            ids = new DLS(startState,goalState);
            int dll;
            System.out.println("Enter Depth Limit");
            Scanner inn=new Scanner(System.in);
            dll=inn.nextInt();
		    success = ids.solveIDS(dll,dump_flag);
            if(success)
		{
			
			System.out.println("Nodes expanded: "+ids.getExploredNodes().size());
			System.out.println("Search depth: " + ids.getCurrentNode().getSearchDepth());
			System.out.println("Max Search depth: " + ids.getCurrentNode().getMaxSearchDepth());
			System.out.println("Path to goal: " + ids.getCurrentNode().getPathToGoal());
			
			System.out.println("Cost to goal: " + ids.getCurrentNode().getCostOfPath());
		}
		else
		{
			System.err.println("no solution found!");
		}
            break;

            case "ucs":
            ucs = new UCS(startState,goalState);
		    success = ucs.solveUCS(dump_flag);
            if(success)
            {
                
                System.out.println("Nodes expanded: "+ucs.getExploredNodes().size());
                System.out.println("Search depth: " + ucs.getCurrentNode().getSearchDepth());
                System.out.println("Max Search depth: " + ucs.getCurrentNode().getMaxSearchDepth());
                System.out.println("Path to goal: " + ucs.getCurrentNode().getPathToGoal());
                
                System.out.println("Cost to goal: " + ucs.getCurrentNode().getCostOfPath());
            }
            else
            {
                System.err.println("no solution found!");
            }
            break;

            case "greedy":
            gdy = new Greedy(startState,goalState);
            success = gdy.solveGreedy(dump_flag);
            if(success)
		{			
			System.out.println("Nodes expanded: "+gdy.getExploredNodes().size());
			System.out.println("Search depth: " + gdy.getCurrentNode().getSearchDepth());
			System.out.println("Max Search depth: " + gdy.getCurrentNode().getMaxSearchDepth());
			System.out.println("Path to goal: " + gdy.getCurrentNode().getPathToGoal());
			
			System.out.println("Cost to goal: " + gdy.getCurrentNode().getCostOfPath());
		}
		else
		{
			System.err.println("no solution found!");
		}
                break;
            case "astar" :
                ast = new Astar(startState,goalState);
                success = ast.solveAstar(dump_flag);
                if(success)
            {			
                System.out.println("Nodes expanded: "+ast.getExploredNodes().size());
                System.out.println("Search depth: " + ast.getCurrentNode().getSearchDepth());
                System.out.println("Max Search depth: " + ast.getCurrentNode().getMaxSearchDepth());
                System.out.println("Path to goal: " + ast.getCurrentNode().getPathToGoal());
                
                System.out.println("Cost to goal: " + ast.getCurrentNode().getCostOfPath());
            }
            else
            {
                System.err.println("no solution found!");
            }
                    break;
             

            default:
            ast = new Astar(startState,goalState);
                success = ast.solveAstar(dump_flag);
                if(success)
            {			
                System.out.println("Nodes expanded: "+ast.getExploredNodes().size());
                System.out.println("Search depth: " + ast.getCurrentNode().getSearchDepth());
                System.out.println("Max Search depth: " + ast.getCurrentNode().getMaxSearchDepth());
                System.out.println("Path to goal: " + ast.getCurrentNode().getPathToGoal());
                
                System.out.println("Cost to goal: " + ast.getCurrentNode().getCostOfPath());
            }
            else
            {
                System.err.println("no solution found!");
            }
                
                return;
        }
        

        // If dump flag is set, write the search trace to a file
       
        // Output search results
       
    }
    
    


}



class DFS {
    Integer[][] initialState = new Integer[3][3];
	Node root;
	int generated=0;
	int popped=0;
	Node currentNode;
	Integer[][] goalState = new Integer[3][3]; 

	//Integer[][] goalState = {{1,2,3},{4,5,6},{7,8,0}}; 
	Stack<Node> fringe = new Stack<Node>();
    //List<Node> fringe = new ArrayList<>();
	List<Node> exploredNodes = new ArrayList<>();
	int maxfringe=0;
	public DFS(Integer[][] startState, Integer[][] goalState )
	{
        this.initialState = new Integer[3][3];      
        this.goalState=goalState;
		int index = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				
				if(startState[i][j]==0) {
					root = new Node(0, i, j,initialState,"",0,0,0,0);
				}
				initialState[i][j] =startState[i][j];
				
			}
		}
		root.setState(initialState);
	}
    
	
	public boolean solveDFS(boolean dump_flag)
	{

        boolean solved = false;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String traceFileName = "trace-" + timeStamp + ".txt";
        String filename=(traceFileName);
           
        FileWriter writer = new FileWriter(filename);
            writer.write("Selected Method DFS \n");


		fringe.add(root);
		//popped=popped+1;
      //  bw.write("Running DFS \n");
		while(!fringe.isEmpty())
		{
			
			currentNode = fringe.pop();
			popped=popped+1;
			if(goalReached())
			{
				solved = true;
				//printCurrentState();
				System.out.println("nodes generated:"+generated);						
				System.out.println("nodes popped:"+popped);
				return solved;
			}
			boolean cn = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] cValues = currentNode.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != cValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            cn = true;
                            break;
                        }
                    }
					if(cn==false)
					{
                       
           
            
           
                        writer.write("Generating Neighbors to = ");
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(currentNode.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};\n");
                       
						exploredNodes.add(currentNode);
					
            }
		
            writer.write("Neighbors = \n");
			
			
          for(Node neighbor : currentNode.getNeighbors())
			{
                
                

                    boolean neighborFound = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] neighborValues = neighbor.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != neighborValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            neighborFound = true;
                            break;
                        }
                    }
                  
                    if (neighborFound==false ) {
   
                        
                        fringe.add(neighbor);
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(neighbor.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};  ");   
						generated=generated+1;
                 
                    }
					
           
			}
            writer.write("\nvisited=");
            for (Node n : exploredNodes) {
                int[][] nValues = n.igetValues();
                
                writer.write("{");
                for(int i=0;i<3;i++)
                {
                    writer.write("[");
                    for(int j=0;j<3;j++)
                    {
                        writer.write(nValues[i][j]+" ");
                    }
                    writer.write("]");
                }
                
                writer.write("};;");
                   
            }
            writer.write("\n");
            writer.write("\nfringe=");
            for (Node n : fringe) {
                int[][] nValues = n.igetValues();
                
                writer.write("{");
                for(int i=0;i<3;i++)
                {
                    writer.write("[");
                    for(int j=0;j<3;j++)
                    {
                        writer.write(nValues[i][j]+" ");
                    }
                    writer.write("]");
                }
                
                writer.write("};;");
                   
            }
            writer.write("\n");


            
 
			currentNode.setNeighbors(null);
            
            if(maxfringe< fringe.size())
            {
                maxfringe= fringe.size();
            }

			
		}
        System.out.println("nodes generated:"+generated);						
		System.out.println("nodes popped:"+popped);				
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
    return solved;
	}
    
    public boolean goalReached() {
        boolean success = true;
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!currentNode.getState()[i][j].equals(goalState[i][j])) {
                    success = false;
                    break;
                }
            }
            if (!success) {
                break;
            }
        }
        return success;
    }


	public Integer[][] getInitialState() {
		return initialState;
	}

	public void setInitialState(Integer[][] initialState) {
		this.initialState = initialState;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public Integer[][] getGoalState() {
		return goalState;
	}

	public void setGoalState(Integer[][] goalState) {
		this.goalState = goalState;
	}

	public Stack<Node> getFringe() {
		return fringe;
	}

	
    public void setFringe(Stack<Node> fringe) {
		this.fringe = fringe;
	}

	public List<Node> getExploredNodes() {
		return exploredNodes;
	}

	public void setExploredNodes(List<Node> exploredNodes) {
		this.exploredNodes = exploredNodes;
	}
}



class Astar {
    Integer[][] initialState = new Integer[3][3];
	Node root;
	int generated=0;
	int popped=0;
	Node currentNode;
	Integer[][] goalState = new Integer[3][3]; 

	//Stack<Node> fringe = new Stack<Node>();
    List<Node> fringe = new ArrayList<>();
	List<Node> exploredNodes = new ArrayList<>();
	int maxfringe=0;
	public Astar(Integer[][] startState, Integer[][] goalState )
	{
        this.initialState = new Integer[3][3];      
        this.goalState=goalState;
		int index = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				
				if(startState[i][j]==0) {
					root = new Node(0, i, j,initialState,"",0,0,0,0);
				}
				initialState[i][j] =startState[i][j];
				
			}
		}
		root.setState(initialState);
	}
    
    
	
	public boolean solveAstar(boolean dump_file)
	{
        boolean solved = false;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String traceFileName = "trace-" + timeStamp + ".txt";
        String filename=(traceFileName);
           
        FileWriter writer = new FileWriter(filename);
            writer.write("Selected Method Astar \n");


		fringe.add(root);
		//bw.write("\n Running Astar");
		while(!fringe.isEmpty())
		{
			//List<Node> neigh=new ArrayList<>();
            //neigh=currentNode.getNeighbors();
			
			List<Node> sortedFringe = new ArrayList<>(fringe);
        Collections.sort(sortedFringe, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node1.as.compareTo(node2.as);
            }
        });
			fringe=sortedFringe;
			
			currentNode = fringe.remove(0);
            popped=popped+1;
			
			if(goalReached())
			{
				solved = true;
				//printCurrentState();
				System.out.println("nodes generated:"+generated);						
				System.out.println("nodes popped:"+popped);
                System.out.println(maxfringe);
               // System.out.println(maxfringe);
                System.out.println(exploredNodes.size());
              //  bw.close();
            
				return solved;
			}
			
            boolean cn = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] cValues = currentNode.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != cValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            cn = true;
                            break;
                        }
                    }
					if(cn==false)
					{
                       
           
            
           
                        writer.write("Generating Neighbors to = ");
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(currentNode.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};\n");
                       
						exploredNodes.add(currentNode);
					
            }
		
            writer.write("Neighbors = \n");
          for(Node neighbor : currentNode.getNeighbors() )
			{
                
                

                    boolean neighborFound = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] neighborValues = neighbor.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != neighborValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            neighborFound = true;
                            break;
                        }
                    }
                  

                    if (neighborFound==false ) {
   
                        
                        fringe.add(neighbor);
                       
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(neighbor.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};  ");   
                        generated=generated+1;
                        if(maxfringe< fringe.size())
            {
                maxfringe= fringe.size();
            }
                    
                 
                    }  
           
			}


          for (Node n : exploredNodes) {
                        int[][] nValues = n.igetValues();
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(nValues[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};;");
                           
                    }
                    writer.write("\n");
                    writer.write("\nfringe start");
                    for (Node n : fringe) {
                        int[][] nValues = n.igetValues();
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(nValues[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};;");
                           
                    }
                    writer.write("\n");
        
        
                    
                    
			currentNode.setNeighbors(null);
            
            

			
		}
        
        System.out.println("nodes generated:"+generated);						
        System.out.println("nodes popped:"+popped);
        System.out.println(maxfringe);        
        writer.close();
   
		
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
    return solved;
	}
    
    public boolean goalReached() {
        boolean success = true;
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!currentNode.getState()[i][j].equals(goalState[i][j])) {
                    success = false;
                    break;
                }
            }
            if (!success) {
                break;
            }
        }
        return success;
    }

	

	public Integer[][] getInitialState() {
		return initialState;
	}

	public void setInitialState(Integer[][] initialState) {
		this.initialState = initialState;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public Integer[][] getGoalState() {
		return goalState;
	}

	public void setGoalState(Integer[][] goalState) {
		this.goalState = goalState;
	}

	public List<Node> getFringe() {
		return fringe;
	}

	public void setFringe(List<Node> fringe) {
		this.fringe = fringe;
	}

	public List<Node> getExploredNodes() {
		return exploredNodes;
	}

	public void setExploredNodes(List<Node> exploredNodes) {
		this.exploredNodes = exploredNodes;
	}
}





class Greedy {
    Integer[][] initialState = new Integer[3][3];
	Node root;
	int generated=0;
	int popped=0;
	Node currentNode;
	Integer[][] goalState = new Integer[3][3]; 

	//Stack<Node> fringe = new Stack<Node>();
    List<Node> fringe = new ArrayList<>();
	List<Node> exploredNodes = new ArrayList<>();
	int maxfringe=0;
	public Greedy(Integer[][] startState, Integer[][] goalState )
	{
        this.initialState = new Integer[3][3];      
        this.goalState=goalState;
		int index = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				
				if(startState[i][j]==0) {
					root = new Node(0, i, j,initialState,"",0,0,0,0);
				}
				initialState[i][j] =startState[i][j];
				
			}
		}
		root.setState(initialState);
	}
    
    
	
	public boolean solveGreedy(boolean dump_file)
	{
        boolean solved = false;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String traceFileName = "trace-" + timeStamp + ".txt";
        String filename=(traceFileName);
           
        FileWriter writer = new FileWriter(filename);
            writer.write("Selected Method Greedy \n");


		fringe.add(root);
		
		while(!fringe.isEmpty())
		{
			//List<Node> neigh=new ArrayList<>();
            //neigh=currentNode.getNeighbors();
			
			List<Node> sortedFringe = new ArrayList<>(fringe);
        Collections.sort(sortedFringe, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node1.manhattandist.compareTo(node2.manhattandist);
            }
        });
			fringe=sortedFringe;
			
			currentNode = fringe.remove(0);
            popped=popped+1;
			
			if(goalReached())
			{
				solved = true;
				//printCurrentState();
				System.out.println("nodes generated:"+generated);						
				System.out.println("nodes popped:"+popped);
                System.out.println(maxfringe);
               // System.out.println(maxfringe);
                System.out.println(exploredNodes.size());
				return solved;
			}
			
            boolean cn = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] cValues = currentNode.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != cValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            cn = true;
                            break;
                        }
                    }
					if(cn==false)
					{
                       
           
            
           
                        writer.write("Generating Neighbors to = ");
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(currentNode.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};\n");
                       
						exploredNodes.add(currentNode);
					
            }
		
            writer.write("Neighbors = \n");
		
			
          for(Node neighbor : currentNode.getNeighbors() )
			{
                
                

                    boolean neighborFound = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] neighborValues = neighbor.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != neighborValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            neighborFound = true;
                            break;
                        }
                    }
                  

                    if (neighborFound==false ) {
   
                        
                        fringe.add(neighbor);
                       
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(neighbor.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};  ");   
                        generated=generated+1;
                        if(maxfringe< fringe.size())
            {
                maxfringe= fringe.size();
            }
                    
                 
                    }  
           
			}
            writer.write("\nvisited nodes =");
            for (Node n : exploredNodes) {
                int[][] nValues = n.igetValues();
                
                writer.write("{");
                for(int i=0;i<3;i++)
                {
                    writer.write("[");
                    for(int j=0;j<3;j++)
                    {
                        writer.write(nValues[i][j]+" ");
                    }
                    writer.write("]");
                }
                
                writer.write("};;");
                   
            }
            writer.write("\n");
            writer.write("\nfringe =");
            for (Node n : fringe) {
                int[][] nValues = n.igetValues();
                
                writer.write("{");
                for(int i=0;i<3;i++)
                {
                    writer.write("[");
                    for(int j=0;j<3;j++)
                    {
                        writer.write(nValues[i][j]+" ");
                    }
                    writer.write("]");
                }
                
                writer.write("};;");
                   
            }
            writer.write("\n");

          
			currentNode.setNeighbors(null);
            
            

			
		}
        
        System.out.println("nodes generated:"+generated);						
        System.out.println("nodes popped:"+popped);
        System.out.println(maxfringe);        
		
        //System.out.println(exploredNodes.size());	
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }							
		return solved;
	}
    
    public boolean goalReached() {
        boolean success = true;
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!currentNode.getState()[i][j].equals(goalState[i][j])) {
                    success = false;
                    break;
                }
            }
            if (!success) {
                break;
            }
        }
        return success;
    }

	private void printCurrentState() {
		System.out.println("Current state after: " + currentNode.getPathToGoal());
		for(int i = 0;i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				System.out.print(currentNode.getState()[i][j]+"");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public Integer[][] getInitialState() {
		return initialState;
	}

	public void setInitialState(Integer[][] initialState) {
		this.initialState = initialState;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public Integer[][] getGoalState() {
		return goalState;
	}

	public void setGoalState(Integer[][] goalState) {
		this.goalState = goalState;
	}

	public List<Node> getFringe() {
		return fringe;
	}

	public void setFringe(List<Node> fringe) {
		this.fringe = fringe;
	}

	public List<Node> getExploredNodes() {
		return exploredNodes;
	}

	public void setExploredNodes(List<Node> exploredNodes) {
		this.exploredNodes = exploredNodes;
	}
}





class UCS {
   
	Integer[][] initialState = new Integer[3][3];
	Node root;
	int generated=0;
	int popped=0;
	Node currentNode;
	Integer[][] goalState = new Integer[3][3]; 

	//Stack<Node> fringe = new Stack<Node>();
    List<Node> fringe = new ArrayList<>();
	List<Node> exploredNodes = new ArrayList<>();
	int maxfringe=0;
	public UCS(Integer[][] startState, Integer[][] goalState )
	{
        this.initialState = new Integer[3][3];      
        this.goalState=goalState;
		int index = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				
				if(startState[i][j]==0) {
					root = new Node(0, i, j,initialState,"",0,0,0,0);
				}
				initialState[i][j] =startState[i][j];
				
			}
		}
		root.setState(initialState);
	}
    
	
	public boolean solveUCS(boolean dump_flag)
	{
		boolean solved = false;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String traceFileName = "trace-" + timeStamp + ".txt";
        String filename=(traceFileName);
           
        FileWriter writer = new FileWriter(filename);
            writer.write("Selected Method UCS\n");


		fringe.add(root);
		//popped=popped+1;
		while(!fringe.isEmpty())
		{
			//List<Node> neigh=new ArrayList<>();
            //neigh=currentNode.getNeighbors();
			List<Node> sortedFringe = new ArrayList<>(fringe);
        Collections.sort(sortedFringe, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node1.costOfPath.compareTo(node2.costOfPath);
            }
        });
			fringe=sortedFringe;
		
			currentNode = fringe.remove(0);
			popped=popped+1;
			//exploredNodes.add(currentNode);
			if(goalReached())
			{
				solved = true;
				//printCurrentState();
                System.out.println(maxfringe);
                System.out.println(exploredNodes.size());
				System.out.println("nodes generated:"+generated);						
				System.out.println("nodes popped:"+popped);
				return solved;
			}
            boolean cn = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] cValues = currentNode.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != cValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            cn = true;
                            break;
                        }
                    }
					if(cn==false)
					{
                       
           
            
           
                        writer.write("Generating Neighbors to = ");
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(currentNode.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};\n");
                       
						exploredNodes.add(currentNode);
					
            }
		
            writer.write("Neighbors = \n");
		
			
		
			
          for(Node neighbor : currentNode.getNeighbors() )
			{
                    boolean neighborFound = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] neighborValues = neighbor.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != neighborValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            neighborFound = true;
                            break;
                        }
                    }
                  
                    if (neighborFound==false ) {    
                        fringe.add(neighbor);
                       
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(neighbor.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};  ");   
                        generated=generated+1;
                        if(maxfringe< fringe.size())
            {
                maxfringe= fringe.size();
            }     
                    }  
           
			}
            writer.write("\nvisited nodes =");
            for (Node n : exploredNodes) {
                int[][] nValues = n.igetValues();
                
                writer.write("{");
                for(int i=0;i<3;i++)
                {
                    writer.write("[");
                    for(int j=0;j<3;j++)
                    {
                        writer.write(nValues[i][j]+" ");
                    }
                    writer.write("]");
                }
                
                writer.write("};;");
                   
            }
            writer.write("\n");
            writer.write("\nfringe =");
            for (Node n : fringe) {
                int[][] nValues = n.igetValues();
                
                writer.write("{");
                for(int i=0;i<3;i++)
                {
                    writer.write("[");
                    for(int j=0;j<3;j++)
                    {
                        writer.write(nValues[i][j]+" ");
                    }
                    writer.write("]");
                }
                
                writer.write("};;");
                   
            }
            writer.write("\n");

			currentNode.setNeighbors(null);
            
		}
        
		
        //System.out.println(exploredNodes.size());		
		System.out.println("nodes generated:"+generated);						
		System.out.println("nodes popped:"+popped);
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }							
		return solved;
	}
    
    public boolean goalReached() {
        boolean success = true;
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!currentNode.getState()[i][j].equals(goalState[i][j])) {
                    success = false;
                    break;
                }
            }
            if (!success) {
                break;
            }
        }
        return success;
    }

	private void printCurrentState() {
		System.out.println("Current state after: " + currentNode.getPathToGoal());
		for(int i = 0;i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				System.out.print(currentNode.getState()[i][j]+"");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public Integer[][] getInitialState() {
		return initialState;
	}

	public void setInitialState(Integer[][] initialState) {
		this.initialState = initialState;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public Integer[][] getGoalState() {
		return goalState;
	}

	public void setGoalState(Integer[][] goalState) {
		this.goalState = goalState;
	}

	public List<Node> getFringe() {
		return fringe;
	}

	public void setFringe(List<Node> fringe) {
		this.fringe = fringe;
	}

	public List<Node> getExploredNodes() {
		return exploredNodes;
	}

	public void setExploredNodes(List<Node> exploredNodes) {
		this.exploredNodes = exploredNodes;
	}
}









class DLS {
    Integer[][] initialState = new Integer[3][3];
	Node root;
	int generated=0;
	int popped=0;
	Node currentNode;
	Integer[][] goalState = new Integer[3][3]; 

	//Integer[][] goalState = {{1,2,3},{4,5,6},{7,8,0}}; 
	Stack<Node> fringe = new Stack<Node>();
    //List<Node> fringe = new ArrayList<>();
	List<Node> exploredNodes = new ArrayList<>();
	int maxfringe=0;
	public DLS(Integer[][] startState, Integer[][] goalState )
	{
        this.initialState = new Integer[3][3];      
        this.goalState=goalState;
		int index = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				
				if(startState[i][j]==0) {
					root = new Node(0, i, j,initialState,"",0,0,0,0);
				}
				initialState[i][j] =startState[i][j];
				
			}
		}
		root.setState(initialState);
	}
    
	public boolean solveIDS(int depthLimit,boolean dump_flag)
    {
        for(int i=0;i<depthLimit;i++)
        {
            if(solveDLS(depthLimit,dump_flag)==true)
            {
                return true;
            }
           
            i=i+5;
        }
        return false;
    }
	public boolean solveDLS(int depthLimit,boolean dump_flag)
	{
		boolean solved = false;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String traceFileName = "trace-" + timeStamp + ".txt";
        String filename=(traceFileName);
           
        FileWriter writer = new FileWriter(filename);
            writer.write("Selected Method DLS \n");


		fringe.add(root);
		//popped=popped+1;
		while(!fringe.isEmpty())
		{
			
			currentNode = fringe.pop();
			popped=popped+1;
			if(goalReached())
			{
				solved = true;
				//printCurrentState();
				System.out.println("nodes generated:"+generated);						
				System.out.println("nodes popped:"+popped);
				return solved;
			}
			boolean cn = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] cValues = currentNode.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != cValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            cn = true;
                            break;
                        }
                    }
					if(cn==false)
					{
                       
           
            
           
                        writer.write("Generating Neighbors to = ");
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(currentNode.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};\n");
                       
						exploredNodes.add(currentNode);
					
            }
		
            writer.write("Neighbors = \n");
		
			
			
			
          for(Node neighbor : currentNode.getNeighbors())
			{
                if (currentNode.searchDepth > depthLimit) {
                    break;
                } 
                
                

                    boolean neighborFound = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] neighborValues = neighbor.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != neighborValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            neighborFound = true;
                            break;
                        }
                    }
                  
                    if (neighborFound==false ) {
   
                        
                        fringe.add(neighbor);
                       
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(neighbor.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};  ");   
                        generated=generated+1;
                 
                    }
					 
           
			}
            writer.write("\nvisited nodes =");
            for (Node n : exploredNodes) {
                int[][] nValues = n.igetValues();
                
                writer.write("{");
                for(int i=0;i<3;i++)
                {
                    writer.write("[");
                    for(int j=0;j<3;j++)
                    {
                        writer.write(nValues[i][j]+" ");
                    }
                    writer.write("]");
                }
                
                writer.write("};;");
                   
            }
            writer.write("\n");
            writer.write("\nfringe =");
            for (Node n : fringe) {
                int[][] nValues = n.igetValues();
                
                writer.write("{");
                for(int i=0;i<3;i++)
                {
                    writer.write("[");
                    for(int j=0;j<3;j++)
                    {
                        writer.write(nValues[i][j]+" ");
                    }
                    writer.write("]");
                }
                
                writer.write("};;");
                   
            }
            writer.write("\n");

			
			currentNode.setNeighbors(null);
            
            if(maxfringe< fringe.size())
            {
                maxfringe= fringe.size();
            }

			
		}
        System.out.println("nodes generated:"+generated);						
		System.out.println("nodes popped:"+popped);				
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }							
		return solved;
	}
    
    public boolean goalReached() {
        boolean success = true;
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!currentNode.getState()[i][j].equals(goalState[i][j])) {
                    success = false;
                    break;
                }
            }
            if (!success) {
                break;
            }
        }
        return success;
    }


	public Integer[][] getInitialState() {
		return initialState;
	}

	public void setInitialState(Integer[][] initialState) {
		this.initialState = initialState;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public Integer[][] getGoalState() {
		return goalState;
	}

	public void setGoalState(Integer[][] goalState) {
		this.goalState = goalState;
	}

	public Stack<Node> getFringe() {
		return fringe;
	}

	
    public void setFringe(Stack<Node> fringe) {
		this.fringe = fringe;
	}

	public List<Node> getExploredNodes() {
		return exploredNodes;
	}

	public void setExploredNodes(List<Node> exploredNodes) {
		this.exploredNodes = exploredNodes;
	}
}


class BFS {

    
    Integer[][] initialState = new Integer[3][3];
	Node root;
	int generated=0;
	int popped=0;
	Node currentNode;
	Integer[][] goalState = new Integer[3][3]; 
	//Stack<Node> fringe = new Stack<Node>();
    List<Node> fringe = new ArrayList<>();
	List<Node> exploredNodes = new ArrayList<>();
	int maxfringe=0;
	public BFS(Integer[][] startState, Integer[][] goalState )
	{
        this.initialState = new Integer[3][3];      
        this.goalState=goalState;
		int index = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				
				if(startState[i][j]==0) {
					root = new Node(0, i, j,initialState,"",0,0,0,0);
				}
				initialState[i][j] =startState[i][j];
				
			}
		}
		root.setState(initialState);
	}
    
	
	public boolean solveBFS(boolean dump_flag)
	{
        boolean solved = false;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String traceFileName = "trace-" + timeStamp + ".txt";
        String filename=(traceFileName);
           
        FileWriter writer = new FileWriter(filename);
            writer.write("Selected Method BFS \n");


		fringe.add(root);
		//popped=popped+1;
		while(!fringe.isEmpty())
		{
			
			currentNode = fringe.remove(0);
			popped=popped+1;
			
			if(goalReached())
			{
				solved = true;
				//printCurrentState();
               
               // System.out.println(exploredNodes.size());
				System.out.println("max fringe"+maxfringe);	
				System.out.println("nodes generated:"+generated);						
				System.out.println("nodes popped:"+popped);
				return solved;
			}
			boolean cn = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] cValues = currentNode.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != cValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            cn = true;
                            break;
                        }
                    }
					if(cn==false)
					{
                       
           
            
           
                        writer.write("Generating Neighbors to = ");
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(currentNode.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};\n");
                       
						exploredNodes.add(currentNode);
					
            }
		
            writer.write("Neighbors = \n");
		
			

			
          for(Node neighbor : currentNode.getNeighbors())
			{
                
                

                    boolean neighborFound = false;
                    //boolean neighborFound = false;
                    for (Node n : exploredNodes) {
                        boolean nodesEqual = true;
                        int[][] nValues = n.igetValues();
                        int[][] neighborValues = neighbor.igetValues();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (nValues[i][j] != neighborValues[i][j]) {
                                    nodesEqual = false;
                                    break;
                                }
                            }
                            if (!nodesEqual) {
                                break;
                            }
                        }
                        if (nodesEqual) {
                            neighborFound = true;
                            break;
                        }
                    }
                  
                    if (neighborFound==false) {
   
                        
                        fringe.add(neighbor);
                       
                        
                        writer.write("{");
                        for(int i=0;i<3;i++)
                        {
                            writer.write("[");
                            for(int j=0;j<3;j++)
                            {
                                writer.write(neighbor.state[i][j]+" ");
                            }
                            writer.write("]");
                        }
                        
                        writer.write("};  ");   
                        generated=generated+1;
                        if(maxfringe< fringe.size())
            {
                maxfringe= fringe.size();
            }
                    
                 
                    }  
           
			}
            writer.write("\nvisited nodes =");
            for (Node n : exploredNodes) {
                int[][] nValues = n.igetValues();
                
                writer.write("{");
                for(int i=0;i<3;i++)
                {
                    writer.write("[");
                    for(int j=0;j<3;j++)
                    {
                        writer.write(nValues[i][j]+" ");
                    }
                    writer.write("]");
                }
                
                writer.write("};;");
                   
            }
            writer.write("\n");
            writer.write("\nfringe =");
            for (Node n : fringe) {
                int[][] nValues = n.igetValues();
                
                writer.write("{");
                for(int i=0;i<3;i++)
                {
                    writer.write("[");
                    for(int j=0;j<3;j++)
                    {
                        writer.write(nValues[i][j]+" ");
                    }
                    writer.write("]");
                }
                
                writer.write("};;");
                   
            }
            writer.write("\n");

			currentNode.setNeighbors(null);
            
            

			
		}
        System.out.println("max fringe"+maxfringe);	
		System.out.println("nodes generated:"+generated);						
		System.out.println("nodes popped:"+popped);
        //System.out.println(exploredNodes.size());		
        					
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }							
		return solved;
	}
    
    public boolean goalReached() {
        boolean success = true;
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!currentNode.getState()[i][j].equals(goalState[i][j])) {
                    success = false;
                    break;
                }
            }
            if (!success) {
                break;
            }
        }
        return success;
    }

	private void printCurrentState() {
		System.out.println("Current state after: " + currentNode.getPathToGoal());
		for(int i = 0;i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				System.out.print(currentNode.getState()[i][j]+"");
			}
			System.out.println("");
		}
		System.out.println("");
	}

    public Integer[][] getInitialState() {
		return initialState;
	}

	public void setInitialState(Integer[][] initialState) {
		this.initialState = initialState;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public Integer[][] getGoalState() {
		return goalState;
	}

	public void setGoalState(Integer[][] goalState) {
		this.goalState = goalState;
	}

	public List<Node> getFringe() {
		return fringe;
	}

	
    public void setFringe(Stack<Node> fringe) {
		this.fringe = fringe;
	}

	public List<Node> getExploredNodes() {
		return exploredNodes;
	}

	public void setExploredNodes(List<Node> exploredNodes) {
		this.exploredNodes = exploredNodes;
	}}


class Node {
	Integer[][] goalState = {{1,2,3},{4,5,6},{7,8,0}};
	int i, j;
	Integer nodeVal;
	Integer[][] state = new Integer[3][3];
	List<Node> neighbors = new ArrayList<>();
	String pathToGoal = "";
	Integer costOfPath = 0;
	int searchDepth = 0;
	Integer manhattandist=0;
    Integer as=costOfPath+manhattandist;
	static int maxSearchDepth = 0;
	public Node(Integer nodeVal, int i, int j, Integer[][] state, String pathToGoal,Integer costOfPath,Integer manhattandist,Integer as,Integer searchDepth)
	{
		this.nodeVal = nodeVal;
		this.i = i;
		this.j = j;
		this.state = state;
		this.pathToGoal = pathToGoal;
		this.costOfPath = costOfPath;
		this.searchDepth = searchDepth;
		this.manhattandist=manhattandist;
        this.as= as;
		if(searchDepth > maxSearchDepth)
		{
			maxSearchDepth = searchDepth;
		}
	}
	public static Integer manhattanDistance(Integer[][] state,Integer[][] goal) {
		Integer distance = 0;
		//Integer[][] state1=state.getValues();
		//Integer[][] goal1=goal.getValues();
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				Integer value = state[i][j];
				if (value != 0) {
					Integer[] pos = findPosition(goal, value);
					distance += Math.abs(pos[0] - i) + Math.abs(pos[1] - j);
				}
			}
		}
		return distance;
	}
	
	public static Integer[] findPosition(Integer[][] matrix, Integer value) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == value) {
					return new Integer[]{i, j};
				}
			}
		}
		throw new IllegalArgumentException("Value not found in matrix");
	}
	
    public int[][] igetValues() {
        int[][] values = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                values[i][j] = this.state[i][j];
            }
        }
        return values;
    }
    public Integer[][] getValues() {
        Integer[][] values = new Integer[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                values[i][j] = this.state[i][j];
            }
        }
        return values;
    }
	
	
	
	private void getRightNeighbor() {
		Integer[][] stateTemp = new Integer[3][3];
		for(int k = 0; k < 3; k++)
		{
			for(int l = 0; l < 3; l++)
			{
				stateTemp[k][l] = state[k][l];
			}
		}
		int i1 = i, j1 = j + 1;
		Integer nodeValTemp;
		
		if(j + 1 < 3)
		{
			nodeValTemp = stateTemp[i1][j1];
			Integer temp = stateTemp[i1][j1];
			stateTemp[i1][j1] = stateTemp[i][j];
			stateTemp[i][j] = temp;
			Integer m=manhattanDistance(stateTemp, goalState);
			Node tempNode = new Node(nodeValTemp, i1, j1, stateTemp, pathToGoal+"\n Move"+nodeValTemp+" Left ", (costOfPath+stateTemp[i][j]),m,m+(costOfPath+stateTemp[i][j]),searchDepth+1);
			neighbors.add(tempNode);
		}
		
	}



	private void getLeftNeighbor() {
		Integer[][] stateTemp = new Integer[3][3];
		for(int k = 0; k < 3; k++)
		{
			for(int l = 0; l < 3; l++)
			{
				stateTemp[k][l] = state[k][l];
			}
		}
		int i1 = i, j1 = j - 1;
		Integer nodeValTemp;
		if(j - 1 >= 0)
		{
			nodeValTemp = stateTemp[i1][j1];
			Integer temp = stateTemp[i1][j1];
			stateTemp[i1][j1] = stateTemp[i][j];
			stateTemp[i][j] = temp;
			Integer m=manhattanDistance(stateTemp, goalState);
			Node tempNode = new Node(nodeValTemp, i1, j1, stateTemp, pathToGoal+"\n Move"+nodeValTemp+" Right ", (costOfPath+stateTemp[i][j]),m,m+(costOfPath+stateTemp[i][j]),searchDepth+1);
			neighbors.add(tempNode);
		}
		
	}



	private void getBottomNeighbor() {
		Integer[][] stateTemp = new Integer[3][3];
		for(int k = 0; k < 3; k++)
		{
			for(int l = 0; l < 3; l++)
			{
				stateTemp[k][l] = state[k][l];
			}
		}
		int i1 = i + 1, j1 = j;
		Integer nodeValTemp;
		if(i + 1 < 3)
		{
			nodeValTemp = stateTemp[i1][j1];
			Integer temp = stateTemp[i1][j1];
			stateTemp[i1][j1] = stateTemp[i][j];
			stateTemp[i][j] = temp;
			Integer m=manhattanDistance(stateTemp, goalState);
			Node tempNode = new Node(nodeValTemp, i1, j1, stateTemp, pathToGoal+"\n Move"+nodeValTemp+" Top ", (costOfPath+stateTemp[i][j]),m,m+(costOfPath+stateTemp[i][j]),searchDepth+1);
			neighbors.add(tempNode);
		}
		
	}



	private void getTopNeighbor() {
		Integer[][] stateTemp = new Integer[3][3];
		for(int k = 0; k < 3; k++)
		{
			for(int l = 0; l < 3; l++)
			{
				stateTemp[k][l] = state[k][l];
			}
		}
		int i1 = i - 1, j1 = j;
		Integer nodeValTemp;
		if(i - 1 >= 0)
		{
			nodeValTemp = stateTemp[i1][j1];
			Integer temp = stateTemp[i1][j1];
			stateTemp[i1][j1] = stateTemp[i][j];
			stateTemp[i][j] = temp;
			Integer m=manhattanDistance(stateTemp, goalState);
			Node tempNode = new Node(nodeValTemp, i1, j1, stateTemp, pathToGoal+"\n Move"+nodeValTemp+" Bottom ", (costOfPath+stateTemp[i][j]),m,m+(costOfPath+stateTemp[i][j]),searchDepth+1);
			neighbors.add(tempNode);
		}
		
		
		
	}



	public Node() {
		super();
	}



	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public Integer getNodeVal() {
		return nodeVal;
	}

	public void setNodeVal(Integer nodeVal) {
		this.nodeVal = nodeVal;
	}

	public Integer[][] getState() {
		return state;
	}

	public void setState(Integer[][] state) {
		this.state = state;
	}



	public List<Node> getNeighbors() {
		
		getTopNeighbor();
		getBottomNeighbor();
		
        getLeftNeighbor();
        getRightNeighbor();

        
       
            
     
        
		
		
		return neighbors;
	}

 /*  public List<Node> getSortedNeighbors() {
        List<Node> sortedNeighbors = new ArrayList<>(neighbors);
        Collections.sort(sortedNeighbors, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node1.costOfPath.compareTo(node2.costOfPath);
            }
        });
        return sortedNeighbors;
    }
*/
	public String getPathToGoal() {
		return pathToGoal;
	}



	public void setPathToGoal(String pathToGoal) {
		this.pathToGoal = pathToGoal;
	}



	public void setNeighbors(List<Node> neighbors) {
		this.neighbors = neighbors;
	}

   

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeVal == null) ? 0 : nodeVal.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (nodeVal == null) {
			if (other.nodeVal != null)
				return false;
		} else if (!nodeVal.equals(other.nodeVal))
			return false;
		return true;
	}



	public Integer getCostOfPath() {
		return costOfPath;
	}



	public void setCostOfPath(Integer costOfPath) {
		this.costOfPath = costOfPath;
	}



	public int getSearchDepth() {
		return searchDepth;
	}



	public void setSearchDepth(int searchDepth) {
		this.searchDepth = searchDepth;
	}
	
	public int getMaxSearchDepth() {
		return maxSearchDepth;
	}

	public void setMaxSearchDepth(int maxSearchDepth) {
		this.maxSearchDepth = maxSearchDepth;
	}
	
	
}





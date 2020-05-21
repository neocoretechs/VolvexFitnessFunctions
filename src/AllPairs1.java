/**
 * 
 */


import java.util.Random;

import com.neocoretechs.volvex.Chromosome;
import com.neocoretechs.volvex.Function;
import com.neocoretechs.volvex.fitnessfunctions.FitnessFunction;
import com.neocoretechs.volvex.functions.Variable;
import com.neocoretechs.volvex.objects.Matrix2x2;
import com.neocoretechs.volvex.objects.Matrix3x3;
import com.neocoretechs.volvex.objects.MatrixNxN;
import com.neocoretechs.volvex.objects.Strings;
import com.neocoretechs.volvex.worlds.RelatrixWorld;
import com.neocoretechs.volvex.worlds.World;

/**
 * Expression of fitness function as chromosome function. When using the chromosome function in this way we nullify the functions and
 * depth and provide the means to store the guid as a simple name for semantic retrieval from deep store.<p/>
 * In the designated world, we instantiate this class and provide access to it through the computeRawFitness method. It is presumed that the
 * return type will always be a float.
 * @author groff
 *
 */
public class AllPairs1 extends FitnessFunction {
	private static final long serialVersionUID = 3845408762831534097L;
	public final String[][] seeds = {{ "123456", "234567", "345678", "456789"}};
	final String[][] targs = {{ "121513424543626563", "232624535654737674", "343735646765848785", "454846757876959896"} };
	/**
	 * @param guid
	 */
	public AllPairs1(World w, String guid) {
		super(w, guid);
	}
	/**
	 * @param argTypes
	 * @param returnType
	 */
	public AllPairs1(World w, Class[] argTypes, Class returnType) {
		super(w,argTypes, returnType);
		((RelatrixWorld)w).setStepFactors(1,4);
	}

	/**
	 * 
	 */
	public AllPairs1() {}
	
	  
	// put test specific stuff here
	   public String ranString() {
		   
	        int leftLimit = 97; // letter 'a'
	        int rightLimit = 122; // letter 'z'
	        int targetStringLength = 1;
	        Random random = new Random();
	     
	        String generatedString = random.ints(leftLimit, rightLimit + 1)
	          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	          .limit(targetStringLength)
	          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	          .toString();
	     
	        System.out.println(generatedString);
	        return generatedString;
	    }
	    public String targString(String ranString) {
	    	StringBuilder s = new StringBuilder();
	    	s.append(ranString.charAt(0));
	    	s.append(ranString.charAt(1));
	    	
	    	s.append(ranString.charAt(0));
	       	s.append(ranString.charAt(4));
	       	
	       	s.append(ranString.charAt(0));
	       	s.append(ranString.charAt(2));
	       	
	       	s.append(ranString.charAt(3));
	       	s.append(ranString.charAt(1));
	       	
	       	s.append(ranString.charAt(3));
	       	s.append(ranString.charAt(4));
	       	
	       	s.append(ranString.charAt(3));
	       	s.append(ranString.charAt(2));
	       	
	       	s.append(ranString.charAt(5));
	       	s.append(ranString.charAt(1));
	       	
	       	s.append(ranString.charAt(5));
	       	s.append(ranString.charAt(4));
	       	
	       	s.append(ranString.charAt(5));
	       	s.append(ranString.charAt(2));
	       	
	       	String out = s.toString();
	       	System.out.println(out);
	       	return out;
	    }
	    
	    MatrixNxN mat = new MatrixNxN(5,new int[5][5]);
	    Matrix2x2 mat2 = new Matrix2x2();
	    Matrix3x3 mat3 = new Matrix3x3();
	    Variable vars[] = new Variable[3];
	    Function[] nodes = {   
	            vars[0]=Variable.create("Mat0", mat.getClass()),
	            vars[1]=Variable.create("Mat1", mat2.getClass()),
	            vars[2]=Variable.create("Mat2", mat3.getClass())
	            };
	    
	    /**
	     * All pairs problem.  Start with matrix
	     * a b x
	     * c d
	     * y
	     * we desire the result to be a set of pairs
	     * (a,b) (a,d) (a,x) (c,b) (c,d) (c,x) (y,b) (y,d) (y,x)
	     * "abxcdy"->"abadaxcbcdcxybydyx"
	     * 
	     * 1 2 3
	     * 4 5
	     * 6
	     * (1,2) (1,5) (1,3) (4,2) (4,5) (4,3) (6,2) (6,5) (6,3) 
	     * 
	     * i j k
	     * l m
	     * n
	     * (i,j) (i,m) (i,k) (l,j) (l,m) (l,k) (n,j) (n,m) (n,k)
	     * 
	     * y o j
	     * i m
	     * b
	     * (y,o) (y,m) (y,j) (i,o) (i,m) (i,j) (b,o) (b,m) (b,j)
	     * 
	     * b o f
	     * i n
	     * g
	     * (b,o) (b,n) (b,f) (i,o) (i,n) (i,f) (g,o) (g,n) g,f)
	     *
	     */     
	public Object execute(Chromosome ind) {
        // w.vars[0].set(w.mat);
        //w.vars[1].set(w.mat2);
         //w.vars[2].set(w.mat3);
        //w.genome.setPreload(w.nodes);
        /*w.seeds = new String[(int) w.MaxSteps][(int) w.TestsPerStep];
        //w.targs = new String[(int) w.MaxSteps][(int) w.TestsPerStep];
        for(int i = 0; i < w.MaxSteps; i++) {
      	  for(int j = 0; j < w.TestsPerStep; j++) {
           	 System.out.print("["+i+","+j+"]= ");
           	 w.seeds[i][j] = w.ranString();
           	 w.targs[i][j] = w.targString(w.seeds[i][j]);
            }
        }*/
        /*
        w.argVals = new ArgumentValues();
        // Example of the complicated way to assign test values, in case a programmatic method is desired
        w.argVals.assign(new AssignInterface() {
			@Override
			public Object[][] assign() {
				Object[][] argv = new Object[1][2];
				argv[0][0] = new Strings("abxcdy");
				argv[0][1] = new Strings("abadaxcbcdcxybydyx");
				return argv;
			}
        });
        */
		    	 /*int[][][] nseeds ={  { {'a','b','x'}, 
		    		 					{'c','d', 0 }, 
		    		 					{'y', 0,  0 } },
		    	 					  { {'1','2','3'}, 
		    	 						{'4','5', 0 }, 
		    	 						{'6', 0,  0 } },
		    	 					  {	{'i','j','k'},
		    	 						{'l','m', 0 },
		    	 						{'n', 0,  0 } },
		    	   	 				  {	{'y','o','j'},
		        	 					{'i','m', 0 },
		        	 					{'b', 0,  0 } },
		         	 				  {	{'b','o','f'},
		        	 					{'i','n', 0 },
		        	 					{'g', 0,  0 } }
		    	 						};*/
		    	 //final String[] seeds = { "abxcdy", "123456", "ijklmn", "yojimb", "bofing" };
		    	 //final String[] targs = { "abadaxcbcdcxybydyx", "121513424543626563", "ijimikljlmlknjnmnk", 
		    	//		 "yoymyjioimijbobmbj", "bobnbfioinifgogngf" };
		    	 	 float hits = 0;
		             float rawFit = -1;
		             //Strings var1 = null,var2 = null,var3 = null,var4 = null;

		             Object[][] arg = new Object[1][1];
		             boolean[][] results = new boolean[(int)((RelatrixWorld)world).MaxSteps][(int) ((RelatrixWorld)world).TestsPerStep];
		            
				     for(int test = 0; test < ((RelatrixWorld)world).MaxSteps; test++) {
				    	for(int step = 0; step < ((RelatrixWorld)world).TestsPerStep; step++) {
				    	//Matrix3x3 m = new Matrix3x3(nseeds[step]); 
				    	arg[0][0] = new Strings(seeds[test][step]);
				    	//Strings pad1 = new Strings(String.format("%1$25s", seeds[test][step])); // pad to 25 to get our 5x5 array
				    	//vars[0].set(StringToMatrix.toMatrix(pad1));
				    	Strings res =  (Strings) execute(ind, arg[0]);
				    	//hits = pairsok(ind.getChromosome(0), res, 0, targs[i][step], test, step, hits);   
		                //with 9 tests
				        //int lres = res.data.length();
		                //if( lres > 1 ) {
		                     hits = pairsok(results, res, targs[test][step], test, step, hits);
		                //}
				    	}  
		  
				      }
				      
		             //if( al.data.size() == 1 && ((Strings)(al.data.get(0))).data.equals("d")) hits = 10; // test
		             rawFit = (((RelatrixWorld)world).MinRawFitness - hits);
		             // The SHOWTRUTH flag is set on best individual during run. We make sure to 
		             // place the checkAndStore inside the SHOWTRUTH block to ensure we only attempt to process
		             // the best individual, and this is what occurs in the showTruth method
		             ((RelatrixWorld)world).showTruth(ind, rawFit, results);
		             
		             return rawFit;
		     }
		     
	/**
	* All pairs helper method
	* @param c 
	* @param res 
	*/
    private float pairsok(boolean[][] results, Strings res, String pair, int test, int step, float fhits) {
     	//System.out.println("pairs ok chromo:"+c);
         //String r1 = res.data.substring(selem, eelem);
         //if(r1.equals(pair.substring(selem, eelem)) ) {
    	 if(pair.startsWith(res.data)) {
    		 fhits += ((float)res.data.length())/((float)pair.length()); // between 0 and 1
    	 //if( res.data.equals(pair)) {
        	 ///++hits;
         	 results[test][step] = true;
         	 //System.out.println("test="+test+" step="+step+" "+r1+" "+pair);
         	 // display this for some crazy function calling results action!
         	 //System.out.println("yes:"+r1+" "+pair+" "+elem);
         } else {
        	 results[test][step] = false;
        	 //System.out.println("no:"+r1+" "+pair+" "+elem);
         }
         //System.out.println("pairsok elems="/*+Storage.size(c)*/+" hits:"+hits+" index="+elem+" res:"+r1+" pair:"+pair);
    	 if(World.SHOWTRUTH)
       	 System.out.println("test="+test+" step="+step+" targ="+pair+" res="+res.data+" hits="+fhits+" truth="+results[test][step]+" "+Thread.currentThread().getName());
         return fhits;
     }
     


}

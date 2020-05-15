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
public class LeadingZero1 extends FitnessFunction {
	private static final long serialVersionUID = 3845408762831534097L;
	 final static String[][] testSet = { {"0", "00", "000", "0000", "00000" , "000000"},
				 {"a", "aa", "aaa", "aaaa", "aaaaa" , "aaaaaa"},
				 {"a", "bb", "ccc", "dddd", "eeeee" , "ffffff"}
	 };
	/**
	 * @param guid
	 */
	public LeadingZero1(World w, String guid) {
		super(w, guid);
	}
	/**
	 * @param argTypes
	 * @param returnType
	 */
	public LeadingZero1(World w, Class[] argTypes, Class returnType) {
		super(w,argTypes, returnType);
	}

	/**
	 * 
	 */
	public LeadingZero1() {}
	
	  
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
	    
	    /**
	     * In this test case we are attaching leading zeros to the string value of
	     * a number and comparing the result of the evolved code to the string
	     * value of the original number. The value returned from the evolved code
	     * and the original number have to be the same to have stripped the leading zeros.
	     * The code to not only has to return the original value when there are no
	     * leading zeros, but return a 0 if the input string consists exclusively
	     * of one of more zeros.<br/>
	     * Set testSetNum to the testSet desired to facilitate the testing. The idea is to evolve a more complex
	     * function should we not converge on a solution, or to test a resultant evo
	     */   
		@Override
		public Object execute(Chromosome ind) {
		    	 	 int testSetNum = 2;
		             int hits = 0;
		             //vars[1].set(new Strings("0"));
		             ((RelatrixWorld)world).setStepFactors(50, 6);
		             Strings o=null;
		             // unit test
		             Object[][] argVals = new Object[1][1];
		             int test = 0;
		             boolean[][] results = new boolean[(int) ((RelatrixWorld)world).MaxSteps][(int) ((RelatrixWorld)world).TestsPerStep];
		             // if we strip zeroes, we dont want the index to start at 0, because that increases our zeroes by 1 when added to a string with valueOf(i)
		             for(int i = 1; i <= ((RelatrixWorld)world).MaxSteps; i++) {
		                 int step = 0;
		                 //vars[0].set(new Strings(zers+String.valueOf(i)));
		                 argVals[0][0] = new Strings(testSet[testSetNum][step]+String.valueOf(i));
		                 o = (Strings)ind.execute_object(argVals[0]);
		                 if( o.data.equals(String.valueOf(i)) ) {
		                	 ++hits;
		                	 results[test][step] = true;
		                 } else
		                	 results[test][step] = false;
		                 ++step;
		                 argVals[0][0] = new Strings(testSet[testSetNum][step]+String.valueOf(i));
		                 o = (Strings)ind.execute_object(argVals[0]);
		                 if( o.data.equals(String.valueOf(i)) ) {
		                	 ++hits;
		                 	 results[test][step] = true;
		                 } else
		                	 results[test][step] = false;
		                 ++step;
		                 argVals[0][0] = new Strings(testSet[testSetNum][step]+String.valueOf(i));
		                 o = (Strings)ind.execute_object(argVals[0]);
		                 if( o.data.equals(String.valueOf(i)) ) {
		                	 ++hits;
		                 	 results[test][step] = true;
		                 } else
		                	 results[test][step] = false;
		                 ++step;
		                 argVals[0][0] = new Strings(testSet[testSetNum][step]+String.valueOf(i));
		                 o = (Strings)ind.execute_object(argVals[0]);
		                 if( o.data.equals(String.valueOf(i)) ) {
		                	 ++hits;
		                 	 results[test][step] = true;
		                 } else
		                	 results[test][step] = false;
		                 ++step;
		                 argVals[0][0] = new Strings(testSet[testSetNum][step]+String.valueOf(i));
		                 o = (Strings)ind.execute_object(argVals[0]);
		                 if( o.data.equals(String.valueOf(i)) ) {
		                	 ++hits;
		                 	 results[test][step] = true;
		                 } else
		                	 results[test][step] = false;
		                 ++step;
		                 argVals[0][0] = new Strings(testSet[testSetNum][step]+String.valueOf(i));
		                 o = (Strings)ind.execute_object(argVals[0]);
		                 if( o.data.equals(String.valueOf(i)) ) {
		                	 ++hits;
		                 	 results[test][step] = true;
		                 } else
		                	 results[test][step] = false;
		               ++test;
		             }
		             float rawFit = (((RelatrixWorld)world).MinRawFitness - (float)(hits));
		             // The SHOWTRUTH flag is set on best individual during run. We make sure to 
		             // place the checkAndStore inside the SHOWTRUTH block to ensure we only attempt to process
		             // the best individual, that happens in the showTruth method
		             //System.out.println("Hits: "+hits);
		             ((RelatrixWorld)world).showTruth(ind, rawFit, results);
		             return rawFit;
		     }
		     /**
		      * We attempt to evolve a function that removes a random
		      * number of leading zeroes from a string.  We loop an int
		      * and inject the random zeroes on front of the string form<br>
		      * We then compare the string form with the value returned from
		      * the function.  This means that not only does it need to strip
		      * zeroes, but if the value is 0 return a string 0, dont just strip zeroes.
		      * Sorry this didnt work, cant optimize to a moving target
		      */
		      /*
		      public float computeRawFitness(Individual ind) {
		              hits = 0;
		              setStepFactors(50, 1);
		              // unit test
		              if( ind.getSequence() == 0 ) {
		                      zernum = new Random().nextInt(5);
		                      zers = "000000".substring(zernum);
		                      System.out.println("Zeroes "+zers);
		              }
		              for(int i = 0; i < MaxSteps; i++) {
		                      vars[0].set(new Strings(zers+String.valueOf(i)));
		                      Strings n = (Strings)ind.execute_object(0, World.noargs);
		                      if( n.data.equals(String.valueOf(i)) )
		                                ++hits;
		              }
		              //System.out.println("Hits: "+hits);
		              return (MinFitnessValue - (float)(hits));
		      }
		      */
		      /**
		      * No way, this target really moves
		      /*
		      public float computeRawFitness(Individual ind) {
		              hits = 0;
		              setStepFactors(50, 1);
		              // unit test
		              for(int i = 0; i < MaxSteps; i++) {
		                      zernum = new Random().nextInt(5);
		                      zers = "000000".substring(zernum);
		                      vars[0].set(new Strings(zers+String.valueOf(i)));
		                      Strings n = (Strings)ind.execute_object(0, World.noargs);
		                      if( n.data.equals(String.valueOf(i)) )
		                                ++hits;
		              }
		              //System.out.println("Hits: "+hits);
		              return (MinRawFitness - (float)(hits));
		      }
		      */
		     /**
		      * String test
		      * If the evolved code comes back and solves it,
		      * the fitness is upped
		      */
		      /*
		      public float computeRawFitness(Individual ind) {
		              hits = 0;
		              setStepFactors(2500, 1);
		              // unit test
		              for(int i = 0; i < 50; i++) {
		                      vars[0].set(new Strings("A:"+String.valueOf(i)));
		                      for(int k = 0 ; k < 50; k++) {
		                              vars[1].set(new Strings("B:"+String.valueOf(k)));
		                              Strings j = (Strings)(ind.execute_object(0, World.noargs));
		                              // up the hits if solved for this test case
		                              Strings res = new Strings("A:"+String.valueOf(i)+"B:"+String.valueOf(k));
		                              if( j.data.equals(res.data) )  ++hits;
		                      }
		              }
		              //System.out.println("Hits: "+hits);
		              return (MinRawFitness - (float)(hits));
		      }
		      */
		      /**
		      * Substring test
		      * If the evolved code comes back and solves it,
		      * the fitness is upped
		      */
		     /*
		      public float computeRawFitness(Individual ind) {
		              hits = 0;
		              setStepFactors(50, 1);
		              // unit test
		              for(int i = 0; i < 50; i++) {
		                      vars[0].set(new Strings("0000"+String.valueOf(i)));
		                      Strings j = (Strings)(ind.execute_object(0, World.noargs));
		                      if( j.data.equals(String.valueOf(i)) )
		                                ++hits;
		              }
		              //System.out.println("Hits: "+hits);
		              return (MinRawFitness - (float)(hits));
		      }
		      */
}

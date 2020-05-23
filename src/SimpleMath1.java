import java.util.Random;

import com.neocoretechs.volvex.Chromosome;
import com.neocoretechs.volvex.fitnessfunctions.FitnessFunction;
import com.neocoretechs.volvex.worlds.RelatrixWorld;
import com.neocoretechs.volvex.worlds.World;

/**
 * Expression of fitness function as chromosome function. When using the chromosome function in this way we nullify the functions and
 * depth and provide the means to store the guid as a simple name for semantic retrieval from deep store.<p/>
 * In the designated world, we instantiate this class and provide access to it through the computeRawFitness method. It is presumed that the
 * return type will always be a float.
 * In this example, discover a function to add or subtract 1, given 2 values. If the first randomly generated value is less or equal
 * to the second, discover a function that adds 1, if first value is greater than second, discover function that subtracts 1
 * @author Groff (C) NeoCoreTechs 5/2020
 *
 */
public class SimpleMath1 extends FitnessFunction {
	private static final long serialVersionUID = -328949489979118002L;
	static int l, k;
	static Object[] argVals = new Object[2];
	static {
		Random r = new Random();
		k = r.nextInt();
		l = r.nextInt();
		System.out.println("l="+l+" k="+k);
		argVals[0] = new Integer(l);
		argVals[1] = new Integer(k);
	}
	/**
	 * @param guid
	 */
	public SimpleMath1(World w, String guid) {
		super(w, guid);
	}
	/**
	 * @param argTypes
	 * @param returnType
	 */
	public SimpleMath1(World w, Class[] argTypes, Class returnType) {
		super(w,argTypes, returnType);
	}

	/**
	 * 
	 */
	public SimpleMath1() {}
	
	// put test specific stuff here
	/**
	 * Discover a function to add or subtract 1 given 2 values. If the first value is less or equal
	 * to the second, discover a function that adds 1, if first value is greater than second, discover function that subtracts 1
	 */
	@Override
	public Object execute(Chromosome ind) {
		      int hits = 0;
		      int test = 0;
		      boolean[][] results = new boolean[(int) ((RelatrixWorld)world).MaxSteps][(int) ((RelatrixWorld)world).TestsPerStep];
		      // unit test
		      // each time this individual passes, up the hits
		      for(int i = 0; i < ((RelatrixWorld)world).MaxSteps; i++) {
		             int step = 0;
		             int j = ind.execute_int(argVals);
		             //System.out.println("j="+j+" k="+k+" l="+l);
		             // up the hits if solved for this test case
		             if( l <= k )
		                 if( j == l+1) {  
		                    ++hits;
		                    results[test][step] = true;
		                 } else
		                    results[test][step] = false;
		             else
		            	 if( l > k )
		                     if( j == l-1) {
		                       ++hits;
		                       results[test][step] = true;
		                     } else
		                       results[test][step] = false;
		                 //System.out.println(i+" "+j);
		                 //if( j == ( k * l ) ){
		                //	 ++hits;
		                  // 	 results[test][step] = true;
		                 //} else
		                	// results[test][step] = false;
		             ++test;
		      }
		      //System.out.println("Hits: "+hits);
		      float rawFit = ((RelatrixWorld)world).MinRawFitness - (float)(hits);
		      // The SHOWTRUTH flag is set on best individual during run. We make sure to 
		      // place the checkAndStore inside the SHOWTRUTH block to ensure we only attempt to process
		      // the best individual, this is what happens in the showTruth method.
		      ((RelatrixWorld)world).showTruth(ind, rawFit, results);
		      return rawFit;
	}

}

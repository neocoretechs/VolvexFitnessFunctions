import java.util.Random;

import com.neocoretechs.volvex.Chromosome;
import com.neocoretechs.volvex.fitnessfunctions.FitnessFunction;
import com.neocoretechs.volvex.worlds.RelatrixWorld;
import com.neocoretechs.volvex.worlds.World;

/**
 * Test of the fitness functions used to evolve the chromosomes in the simple math series. A test is a type of function that
 * resembles a fitness function in function and form, however, it is used in the test scenario rath than evolution. It is designed to deliver a
 * range of values for evaluation visually.
 * @author groff
 *
 */
public class SimpleMathTest2 extends FitnessFunction {
	private static final long serialVersionUID = -328949489979118002L;
	Random r;
	int k,l;
	/**
	 * @param guid
	 */
	public SimpleMathTest2(World w, String guid) {
		super(w, guid);
		r = new Random();
	}
	/**
	 * @param argTypes
	 * @param returnType
	 */
	public SimpleMathTest2(World w, Class[] argTypes, Class returnType) {
		super(w,argTypes, returnType);
		r = new Random();
	}

	public SimpleMathTest2() {
		r = new Random();
	}
	
	// put test specific stuff here
    /**
     * X**2 + 2
     */
	@Override
	public Object execute(Chromosome ind) {
	    Object[] argVals = new Object[2];
	         int hits = 0;
	         int test = 0;
	         boolean[][] results = new boolean[(int) ((RelatrixWorld)world).MaxSteps][(int) ((RelatrixWorld)world).TestsPerStep];
	         // unit test
	         // each time this individual passes, up the hits
	         for(int i = 0; i < 100; i++) {
	             int step = 0;
	             	//k = r.nextInt();
	             	 argVals[0] = new Integer(i);
	             	 argVals[1] = new Integer(i);
	             	 System.out.println("Preparing to execute with "+k+" test "+test+" step "+step+" with tests="+((RelatrixWorld)world).MaxSteps+" steps="+((RelatrixWorld)world).TestsPerStep);
	                 int j = ind.execute_int(argVals);
	                 //System.out.println(i+" "+j);
	                 if( j == ( (i*i)+2 ) ){ // did we evolve i**2 + 2 or arg1 * arg2 + 2?
	                	 ++hits;
	                   	 results[test][step] = true;
	                 } else
	                	 results[test][step] = false;
	             ++test;
	         }
	         //System.out.println("Hits: "+hits);
	         float rawFit = (((RelatrixWorld)world).MinRawFitness - (float)(hits));
	         // The SHOWTRUTH flag is set on best individual during run. We make sure to 
	         // place the checkAndStore inside the SHOWTRUTH block to ensure we only attempt to process
	         // the best individual, this is what happens in the showTruth method.
	         ((RelatrixWorld)world).showTruth(ind, rawFit, results);
	         return rawFit;
	}

}

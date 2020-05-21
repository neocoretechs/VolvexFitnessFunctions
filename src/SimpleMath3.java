import com.neocoretechs.volvex.Chromosome;
import com.neocoretechs.volvex.fitnessfunctions.FitnessFunction;
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
public class SimpleMath3 extends FitnessFunction {
	private static final long serialVersionUID = -328949489979118002L;
	int l, k;
    Object[][] argVals = new Object[1][2];
	/**
	 * @param guid
	 */
	public SimpleMath3(World w, String guid) {
		super(w, guid);
	}
	/**
	 * @param argTypes
	 * @param returnType
	 */
	public SimpleMath3(World w, Class[] argTypes, Class returnType) {
		super(w,argTypes, returnType);
	}

	public SimpleMath3() {}
	
	// put test specific stuff here
    /**
     * X**2 + 2
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
	                 //vars[0].set(new Integer(i));
	             	 argVals[0][0] = new Integer(i);
	             	 argVals[0][1] = argVals[0][0];
	                 int j = ind.execute_int(argVals[0]);
	                 //System.out.println(i+" "+j);
	                 if( j == ( (i*i)+2 ) ){
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

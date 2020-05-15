import com.neocoretechs.volvex.Chromosome;
import com.neocoretechs.volvex.Function;
import com.neocoretechs.volvex.fitnessfunctions.FitnessFunction;
import com.neocoretechs.volvex.functions.Variable;
import com.neocoretechs.volvex.objects.Matrix3x3;
import com.neocoretechs.volvex.objects.MatrixNxN;
import com.neocoretechs.volvex.objects.Strings;
import com.neocoretechs.volvex.worlds.RelatrixWorld;
import com.neocoretechs.volvex.worlds.World;

public class Multiplexer1 extends FitnessFunction {
	private static final long serialVersionUID = -6456050967277445199L;
	  // This is so that we don't have to use the Variable's Hashtable to get
	  // values all the time, which is time-consuming.
	  Variable[] vars = new Variable[8];
	  Function[] nodeSets = {   
		        vars[0]=Variable.create("D0", Function.booleanClass),
		        vars[1]=Variable.create("D1", Function.booleanClass),
		        vars[2]=Variable.create("D2", Function.booleanClass),
		        vars[3]=Variable.create("D3", Function.booleanClass),
		        vars[4]=Variable.create("D4", Function.booleanClass),
		        vars[5]=Variable.create("D5", Function.booleanClass),
		        vars[6]=Variable.create("D6", Function.booleanClass),
		        vars[7]=Variable.create("D7", Function.booleanClass) 
		      };

	public Multiplexer1(World w) {
		super(w);
	}

	public Multiplexer1(World w, String guid) {
		super(w, guid);
	}

	public Multiplexer1(World w, Class[] argTypes, Class returnType) {
		super(w, argTypes, returnType);
		// TODO Auto-generated constructor stub
	}

	public Multiplexer1(World w, int size, Function[] functionSet, Class[] argTypes, Class returnType) {
		super(w, size, functionSet, argTypes, returnType);
		// TODO Auto-generated constructor stub
	}

	public Multiplexer1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(Chromosome ind) {
		   int hits = 0;
		    int mask = 1;
		    int bit;
		    Object[][] argVals = new Object[1][3];
		    // find a formula to choose
		    // one of eight boolean inputs based on the values of three other boolean inputs.
		    boolean[][] results = new boolean[(int)((RelatrixWorld)world).MaxSteps][(int) ((RelatrixWorld)world).TestsPerStep];
		    int step = 0;
		    for (int addr=0; addr<8; addr++, mask<<=1) { // 8 inputs, 8 steps
		      /*vars[8].set(*/ argVals[0][0] = (addr&1)==0 ? Boolean.FALSE : Boolean.TRUE;// );
		      /*vars[9].set(*/ argVals[0][1] = (addr&2)==0 ? Boolean.FALSE : Boolean.TRUE; //);
		      /*vars[10].set(*/argVals[0][2] = (addr&4)==0 ? Boolean.FALSE : Boolean.TRUE; //);

		      // Set the variables to their proper values based on i. We check
		      // to see if a given variable needs to change, and we don't change
		      // it if it doesn't need to be changed. This is for efficiency.

		      for (int data=0; data<256; data++) { // 256 tests * 8 steps (inputs) = 2048 MaxSteps
		    	int test = 0;
		        bit=0;
		        // find a formula to choose
		        // one of eight boolean inputs based on the values of three other boolean inputs. 3 steps per test
		        for (int dmask=1; (dmask&0x100)==0; dmask<<=1, bit++) // 3 inputs
		          if (data==0 || (data&dmask)!=((data-1)&dmask))
		            vars[bit].set( (data&dmask)==0 ? Boolean.FALSE : Boolean.TRUE );

		        if (ind.execute_boolean(argVals[0] /*World.noargs*/)==((data&mask)!=0)) {
		          hits++;
		       	  results[test][step] = true;
		        } else {
		    	  results[test][step] = false;
		        }
		        ++test;
		      }
		      ++step;
		    }
		    // return hits;
		    float rawFit = (((RelatrixWorld)world).MinRawFitness - (float)(hits));
		    // The SHOWTRUTH flag is set on best individual during run. We make sure to 
		    // place the checkAndStore inside the SHOWTRUTH block to ensure we only attempt to process
		    // the best individual, that happens in the showTruth method
		    //System.out.println("Hits: "+hits);
		    ((RelatrixWorld)world).showTruth(ind, rawFit, results);
		    return rawFit;
	  }

}

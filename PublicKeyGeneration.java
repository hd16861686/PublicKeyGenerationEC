import java.math.BigInteger;
import java.util.Random;
import java.security.SecureRandom;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.ECNamedCurveTable;

public class PublicKeyGeneration {
	public static void main(String[] argv) {
		    System.out.println(" --- PARAMETROS CURVA ELIPTICA/ELLIPTIC CURVE PARAMETERS ---");
		    ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256r1");
		    System.out.println("Curva/Curve: P-256" );
		    ECPoint G = ecSpec.getG();
		    BigInteger Gx = G.getAffineXCoord().toBigInteger();
		    BigInteger Gy = G.getAffineYCoord().toBigInteger();
		    System.out.println("Gx(Hex): " + String.format("%x", Gx));
		    System.out.println("Gy(Hex): " + String.format("%x", Gy));
		    System.out.println("");
		    System.out.println(" --- LLAVE PRIVADA/PRIVATE KEY ---");
				Random rnd = new SecureRandom();
				BigInteger r = new BigInteger("115792089210356248762697446949407573529996955224135760342422259061068512044369");

				BigInteger private_key;
						do {
			    		private_key = new BigInteger(256,rnd);
						}
						while (private_key.compareTo(r) == 1 || private_key.compareTo(r) == 0 || private_key.bitLength()!=256);

				System.out.println("Bit private_key:  "+ private_key.bitLength());
		    System.out.println("SK: " + String.format("%064d", private_key));
		    System.out.println("SK(Hex): " + String.format("%064x", private_key));
		    System.out.println("Longitud/Length SK(Bytes): " + String.format("%064x", private_key).length()/2);
		    System.out.println("");

			  System.out.println(" --- LLAVE PUBLICA/PUBLIC KEY ---");

		    ECPoint Q = ecSpec.getG().multiply(private_key);
		    ECPoint P=Q.multiply(BigInteger.valueOf(1));
		    BigInteger public_key=new BigInteger((P.getEncoded(true)));
	      System.out.println("Bit public_key:  "+ public_key.bitLength());
			  System.out.println("PK: " + public_key);
		    System.out.println("PK(Hex): " + String.format("%x", public_key));
		    System.out.println("Longitud/Length PK(Bytes): " + String.format("%x", public_key).length()/2); //Not 32 bytes!

		    BigInteger Px = P.getAffineXCoord().toBigInteger();
		    BigInteger Py = P.getAffineYCoord().toBigInteger();
		    System.out.println("Px(Hex): " + String.format("%x", Px));
		    System.out.println("Py(Hex): " + String.format("%x", Py));
		  }
	}

package solidity.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.2.1.
 */
public final class Greeter extends Contract {
    private static final String BINARY = "6060604052346100005760405161043c38038061043c833981016040528051015b8060009080519060200190828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061006c57805160ff1916838001178555610099565b82800160010185558215610099579182015b8281111561009957825182559160200191906001019061007e565b5b506100ba9291505b808211156100b657600081556001016100a2565b5090565b50505b505b61036e806100ce6000396000f300606060405263ffffffff60e060020a60003504166376dbd108811461003a578063a41368621461009f578063cfae3217146100f4575b610000565b346100005761008d600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375094965061018195505050505050565b60408051918252519081900360200190f35b34610000576100f2600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375094965061020595505050505050565b005b34610000576101016102a5565b604080516020808252835181830152835191928392908301918501908083838215610147575b80518252602083111561014757601f199092019160209182019101610127565b505050905090810190601f1680156101735780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60006002826000604051602001526040518082805190602001908083835b602083106101be5780518252601f19909201916020918201910161019f565b51815160209384036101000a600019018019909216911617905260405191909301945091925050808303816000866161da5a03f1156100005750506040515190505b919050565b8060009080519060200190828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061025157805160ff191683800117855561027e565b8280016001018555821561027e579182015b8281111561027e578251825591602001919060010190610263565b5b5061029f9291505b8082111561029b5760008155600101610287565b5090565b50505b50565b604080516020808201835260008083528054845160026001831615610100026000190190921691909104601f8101849004840282018401909552848152929390918301828280156103375780601f1061030c57610100808354040283529160200191610337565b820191906000526020600020905b81548152906001019060200180831161031a57829003601f168201915b505050505090505b905600a165627a7a723058206d8119cb9844f77b659b82329dbbd1f970c5995c9540c9358ec2e09b6917e78c0029";

    private Greeter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Greeter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<Bytes32> calculateProof(Utf8String document) {
        Function function = new Function("calculateProof", 
                Arrays.<Type>asList(document), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setGreeting(Utf8String _greeting) {
        Function function = new Function("setGreeting", Arrays.<Type>asList(_greeting), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> greet() {
        Function function = new Function("greet", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<Greeter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Utf8String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_greeting));
        return deployAsync(Greeter.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<Greeter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Utf8String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_greeting));
        return deployAsync(Greeter.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Greeter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Greeter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Greeter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Greeter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}

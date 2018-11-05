package org.elastos.api;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.elastos.ela.Ela;
import org.elastos.ela.RawTx;
import org.elastos.ela.TxOutput;
import org.elastos.ela.payload.PayloadInvoke;
import org.elastos.ela.utxoTxInput;
import org.elastos.ela.contract.FunctionCode;
import org.elastos.ela.payload.PayloadDeploy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

import static org.elastos.api.Basic.genfunctionCode;

public class NeoContractTransaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeoContractTransaction.class);

    public static String genDeyplyContractTransaction(JSONObject inputsAddOutpus){
        try {
            final JSONArray transaction = inputsAddOutpus.getJSONArray("Transactions");
            JSONObject json_transaction = (JSONObject) transaction.get(0);
            final JSONArray utxoInputs = json_transaction.getJSONArray("UTXOInputs");
            final JSONArray outputs = json_transaction.getJSONArray("Outputs");

            //解析inputs
            utxoTxInput[] utxoTxInputs = Basic.parseInputs(utxoInputs).toArray(new utxoTxInput[utxoInputs.size()]);
            // outputs
            TxOutput[] txOutputs = Basic.parseOutputsAmountStr(outputs).toArray(new TxOutput[outputs.size()]);
            //functionCode
            FunctionCode functionCode = genfunctionCode(json_transaction);
            //PayloadDeploy
            PayloadDeploy payloadDeploy = Basic.parsePayloadDeploy(json_transaction);

            LinkedHashMap<String, Object> resultMap = new LinkedHashMap<String, Object>();
            RawTx rawTx = Ela.deployContractTransaction(utxoTxInputs,txOutputs,functionCode,payloadDeploy);
            resultMap.put("rawTx", rawTx.getRawTxString());
            resultMap.put("txHash", rawTx.getTxHash());

            LOGGER.info(Basic.getSuccess("genDeyplyContractTransaction" ,resultMap));
            return Basic.getSuccess("genDeyplyContractTransaction" , resultMap);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return e.toString();
        }
    }

    public static String genInvoknContractTransaction(JSONObject inputsAddOutpus){
        try {
            final JSONArray transaction = inputsAddOutpus.getJSONArray("Transactions");
            JSONObject json_transaction = (JSONObject) transaction.get(0);
            final JSONArray utxoInputs = json_transaction.getJSONArray("UTXOInputs");
            final JSONArray outputs = json_transaction.getJSONArray("Outputs");

            //解析inputs
            utxoTxInput[] utxoTxInputs = Basic.parseInputs(utxoInputs).toArray(new utxoTxInput[utxoInputs.size()]);
            // outputs
            TxOutput[] txOutputs = Basic.parseOutputsAmountStr(outputs).toArray(new TxOutput[outputs.size()]);
            // invoke
            PayloadInvoke payloadInvoke = Basic.genPayloadInvoke(json_transaction);

            LinkedHashMap<String, Object> resultMap = new LinkedHashMap<String, Object>();
            RawTx rawTx = Ela.invokenContractTransaction(utxoTxInputs,txOutputs,payloadInvoke);
            resultMap.put("rawTx", rawTx.getRawTxString());
            resultMap.put("txHash", rawTx.getTxHash());

            LOGGER.info(Basic.getSuccess("genInvoknContractTransaction" ,resultMap));
            return Basic.getSuccess("genInvoknContractTransaction" , resultMap);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return e.toString();
        }
    }

}
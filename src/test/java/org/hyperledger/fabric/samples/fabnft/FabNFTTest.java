package org.hyperledger.fabric.samples.fabnft;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

public class FabNFTTest {
    private final class MockKeyValue implements KeyValue {
        private final String key;
        private final String value;

        private MockKeyValue(final String key, final String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getStringValue() {
            return value;
        }

        @Override
        public byte[] getValue() {
            return value.getBytes();
        }
    }
    private final class MockNFTResultsIterator implements QueryResultsIterator<KeyValue> {
        private final List<KeyValue> NFTList;
        MockNFTResultsIterator(){
            super();
            NFTList = new ArrayList<KeyValue>();
            NFTList.add(new MockKeyValue("NFT0","{\"id\":\"1\",\"owner\":\"Jason\",\"fileFinger\":\"123\",\"name\":\"NTF1\",\"description\":\"first NFT\",\"date\":\"2023/3/8\",\"price\":\"20\",\"IPFSHash\":\"123\"}" ));
            NFTList.add(new MockKeyValue("NFT1","{\"id\":\"2\",\"owner\":\"Jason\",\"fileFinger\":\"123\",\"name\":\"NTF2\",\"description\":\"second NFT\",\"date\":\"2023/3/8\",\"price\":\"20\",\"IPFSHash\":\"123\"}" ));
            NFTList.add(new MockKeyValue("NFT2","{\"id\":\"3\",\"owner\":\"Jason\",\"fileFinger\":\"123\",\"name\":\"NTF3\",\"description\":\"third NFT\",\"date\":\"2023/3/8\",\"price\":\"20\",\"IPFSHash\":\"123\"}" ));
        }
        @Override
        public Iterator<KeyValue> iterator() {
            return NFTList.iterator();
        }
        @Override
        public void close() throws Exception {
            // TODO Auto-generated method stub
        }
    }

    @Test
    public void invokeUnKnownTransaction() {
        FabNFT contract = new FabNFT();
        Context ctx = mock(Context.class);

        Throwable thrown = catchThrowable(() -> {
            contract.unknownTransaction(ctx);
        });

        assertThat(thrown)
            .isInstanceOf(ChaincodeException.class)
            .hasNoCause().hasMessage("Undefined contract method called");
        assertThat(((ChaincodeException) thrown).getPayload()).isEqualTo(null);
        verifyZeroInteractions(ctx);
    }
    @Nested
    class InvokeQueryNFTTransaction {
        @Test
        public void whenNFTExists() {
            FabNFT contract = new FabNFT();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState("NFT0")).thenReturn("{\"id\":\"1\",\"owner\":\"Jason\",\"fileFinger\":\"123\",\"name\":\"NTF1\",\"description\":\"first NFT\",\"date\":\"2023/3/8\",\"price\":\"20\",\"IPFSHash\":\"123\"}" );

            NFT result = contract.queryNFT(ctx, "NFT0");

            assertThat(result).isEqualTo(new NFT("1","Jason","123","NTF1","first NFT","2023/3/8","20","123"));
        }
        @Test
        public void whenNFTDoesNotExist() {
            FabNFT contract = new FabNFT();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState("NFT0")).thenReturn("");

            Throwable thrown = catchThrowable(() -> {
                contract.queryNFT(ctx, "NFT0");
            });

            assertThat(thrown).isInstanceOf(ChaincodeException.class).hasNoCause()
                    .hasMessage("NFT NFT0 does not exist");
            assertThat(((ChaincodeException) thrown).getPayload()).isEqualTo("NFT_NOT_FOUND".getBytes());
        }
    }
    @Test
    void invokeInitLedgerTransaction() {
        FabNFT contract = new FabNFT();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);

        contract.initLeger(ctx);

        InOrder inOrder = inOrder(stub);
        inOrder.verify(stub).putStringState("NFT1", "{\"date\":\"2023/3/8\",\"description\":\"first NFT\",\"fileFinger\":\"123\",\"id\":\"1\",\"iPFSHash\":\"123\",\"name\":\"NTF1\",\"owner\":\"Jason\",\"price\":\"20\"}");
        inOrder.verify(stub).putStringState("NFT2", "{\"date\":\"2023/3/8\",\"description\":\"second NFT\",\"fileFinger\":\"123\",\"id\":\"2\",\"iPFSHash\":\"123\",\"name\":\"NTF2\",\"owner\":\"Jason\",\"price\":\"20\"}");
        inOrder.verify(stub).putStringState("NFT3", "{\"date\":\"2023/3/8\",\"description\":\"third NFT\",\"fileFinger\":\"123\",\"id\":\"3\",\"iPFSHash\":\"123\",\"name\":\"NTF3\",\"owner\":\"Jason\",\"price\":\"20\"}");
    }
    @Nested
    class InvokeCreateNFTTransaction{
        @Test
        public void whenNFTExists(){
            FabNFT contract = new FabNFT();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState("NFT0")).thenReturn("{\"id\":\"1\",\"owner\":\"Jason\",\"fileFinger\":\"123\",\"name\":\"NTF1\",\"description\":\"first NFT\",\"date\":\"2023/3/8\",\"price\":\"20\",\"IPFSHash\":\"123\"}" );
            Throwable thrown = catchThrowable(() -> {
                contract.createNFT(ctx, "NFT0","1", "Jason", "123", "NTF1", "first NFT", "2023/3/8", "20", "123");
            });
            assertThat(thrown).isInstanceOf(ChaincodeException.class).hasNoCause()
                    .hasMessage("NFT NFT0 already exists");
            assertThat(((ChaincodeException) thrown).getPayload()).isEqualTo("NFT_ALREADY_EXISTS".getBytes());
        }
        @Test
        public void whenNFTDostNotExist(){
            FabNFT contract = new FabNFT();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState("NFT0")).thenReturn("");
            NFT nft =  contract.createNFT(ctx, "NFT0","1", "Jason", "123", "NTF1", "first NFT", "2023/3/8", "20", "123");
            assertThat(nft).isEqualTo(new NFT("1","Jason","123","NTF1","first NFT","2023/3/8","20","123"));
        }

    }
    @Test
    void invokeQueryAllCarsTransaction() {
        FabNFT contract = new FabNFT();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);
        when(stub.getStateByRange("NFT0", "NFT999")).thenReturn(new MockNFTResultsIterator());

        String result = contract.queryAllNFTs(ctx);
        assertThat(result).isEqualTo("[{\"key\":\"NFT0\",\"record\":{\"date\":\"2023/3/8\",\"description\":\"first NFT\",\"fileFinger\":\"123\",\"id\":\"1\",\"iPFSHash\":\"123\",\"name\":\"NTF1\",\"owner\":\"Jason\",\"price\":\"20\"}},{\"key\":\"NFT1\",\"record\":{\"date\":\"2023/3/8\",\"description\":\"second NFT\",\"fileFinger\":\"123\",\"id\":\"2\",\"iPFSHash\":\"123\",\"name\":\"NTF2\",\"owner\":\"Jason\",\"price\":\"20\"}},{\"key\":\"NFT2\",\"record\":{\"date\":\"2023/3/8\",\"description\":\"third NFT\",\"fileFinger\":\"123\",\"id\":\"3\",\"iPFSHash\":\"123\",\"name\":\"NTF3\",\"owner\":\"Jason\",\"price\":\"20\"}}]");
        @Nested
        class ChangeNFTOwnerTransaction {
            @Test
            public void whenNFTExists() {
                FabNFT contract = new FabNFT();
                Context ctx = mock(Context.class);
                ChaincodeStub stub = mock(ChaincodeStub.class);
                when(ctx.getStub()).thenReturn(stub);
                when(stub.getStringState("NFT0")).thenReturn("{\"id\":\"1\",\"owner\":\"Jason\",\"fileFinger\":\"123\",\"name\":\"NTF1\",\"description\":\"first NFT\",\"date\":\"2023/3/8\",\"price\":\"20\",\"IPFSHash\":\"123\"}" );
                NFT nft = contract.changeNFTOwner(ctx, "NFT0", "Jason2");
                assertThat(nft).isEqualTo(new NFT("1","Jason2","123","NTF1","first NFT","2023/3/8","20","123"));
            }

            @Test
            public void whenNFTDoesNotExist() {
                FabNFT contract = new FabNFT();
                Context ctx = mock(Context.class);
                ChaincodeStub stub = mock(ChaincodeStub.class);
                when(ctx.getStub()).thenReturn(stub);
                when(stub.getStringState("NFT0")).thenReturn("");
                Throwable throwable = catchThrowable(() -> {
                    contract.changeNFTOwner(ctx, "NFT0",  "Jason2");
                });
                assertThat(throwable).isInstanceOf(ChaincodeException.class).hasNoCause()
                        .hasMessage("NFT NFT0 does not exist");
                assertThat(((ChaincodeException) throwable).getPayload()).isEqualTo("NFT_NOT_FOUND".getBytes());
            }
        }
    }
}

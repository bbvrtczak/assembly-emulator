import BB.memory.Memory;
import BB.service.TransferService;
import org.junit.Assert;
import org.junit.Test;

public class MovInstructionTest {
    @Test
    public void movRegisterToRegisterTest(){
        Memory memory = new Memory();
        TransferService transferService = new TransferService(memory);

        memory.getAx().setValue(10);
        memory.getBx().setValue(1);
        memory.getCx().setValue(10);
        memory.getDx().setValue(1);


        transferService.mov("ax", "bx");
        Assert.assertEquals(memory.getAx().getValue(), 1);

        transferService.mov("cx", "dx");
        Assert.assertEquals(memory.getCx().getValue(), 1);

        memory.getAx().setValue(1);
        memory.getBx().setValue(10);
        memory.getCx().setValue(1);
        memory.getDx().setValue(10);

        transferService.mov("bx", "ax");
        Assert.assertEquals(memory.getBx().getValue(), 1);

        transferService.mov("dx", "cx");
        Assert.assertEquals(memory.getDx().getValue(), 1);
    }

    @Test
    public void movValueToRegisterTest(){
        Memory memory = new Memory();
        TransferService transferService = new TransferService(memory);


        // adding to registers after start-up (all 0)
        transferService.mov("ax", 5);
        Assert.assertEquals(memory.getAx().getValue(), 5);

        transferService.mov("bx", 10);
        Assert.assertEquals(memory.getBx().getValue(), 10);

        transferService.mov("cx", 15);
        Assert.assertEquals(memory.getCx().getValue(), 15);

        transferService.mov("dx", 20);
        Assert.assertEquals(memory.getDx().getValue(), 20);
    }
}

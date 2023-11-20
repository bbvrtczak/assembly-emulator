import BB.memory.Memory;
import BB.service.ArithmeticService;
import org.junit.Assert;
import org.junit.Test;

public class AddInstructionTest {

    @Test
    public void addRegisterToRegisterTest(){
        Memory memory = new Memory();
        ArithmeticService arithmeticService = new ArithmeticService(memory);

        memory.getAx().setValue(5);
        memory.getBx().setValue(6);
        memory.getCx().setValue(3);
        memory.getDx().setValue(9);


        arithmeticService.add("ax", "bx");
        Assert.assertEquals(memory.getAx().getValue(), 11);

        arithmeticService.add("bx", "cx");
        Assert.assertEquals(memory.getBx().getValue(), 9);

        arithmeticService.add("ax", "bx");
        Assert.assertEquals(memory.getAx().getValue(), 20);

        arithmeticService.add("dx", "ax");
        Assert.assertEquals(memory.getDx().getValue(), 29);

        arithmeticService.add("cx", "dx");
        Assert.assertEquals(memory.getCx().getValue(), 32);
    }

    @Test
    public void addValueToRegisterTest(){
        Memory memory = new Memory();
        ArithmeticService arithmeticService = new ArithmeticService(memory);


        // adding to registers after start-up (all 0)
        arithmeticService.add("ax", 5);
        Assert.assertEquals(memory.getAx().getValue(), 5);

        arithmeticService.add("bx", 10);
        Assert.assertEquals(memory.getBx().getValue(), 10);

        arithmeticService.add("cx", 15);
        Assert.assertEquals(memory.getCx().getValue(), 15);

        arithmeticService.add("dx", 20);
        Assert.assertEquals(memory.getDx().getValue(), 20);

        // adding to register when there is some data
        arithmeticService.add("ax", 10);
        Assert.assertEquals(memory.getAx().getValue(), 15);

        arithmeticService.add("bx", 20);
        Assert.assertEquals(memory.getBx().getValue(), 30);

        arithmeticService.add("cx", 5);
        Assert.assertEquals(memory.getCx().getValue(), 20);

        arithmeticService.add("dx", 15);
        Assert.assertEquals(memory.getDx().getValue(), 35);
    }

    @Test
    public void addValueToRegisterWithOverflowTest(){
        Memory memory = new Memory();
        ArithmeticService arithmeticService = new ArithmeticService(memory);

        memory.getAx().setValue(0xFFFF);
        memory.getBx().setValue(0xFFFF);
        memory.getCx().setValue(0xFFFF);
        memory.getDx().setValue(0xFFFF);

        // check if there will be 0 after adding 1 to 0xFFFF
        arithmeticService.add("ax", 1);
        Assert.assertEquals(memory.getAx().getValue(), 0);

        arithmeticService.add("bx", 1);
        Assert.assertEquals(memory.getBx().getValue(), 0);

        arithmeticService.add("cx", 1);
        Assert.assertEquals(memory.getCx().getValue(), 0);

        arithmeticService.add("dx", 1);
        Assert.assertEquals(memory.getDx().getValue(), 0);
    }

    @Test
    public void addRegisterToRegisterWithOverflowTest(){
        Memory memory = new Memory();
        ArithmeticService arithmeticService = new ArithmeticService(memory);

        memory.getAx().setValue(0xFFFF);
        memory.getBx().setValue(0x0001);
        memory.getCx().setValue(0xFFFF);
        memory.getDx().setValue(0x0001);


        arithmeticService.add("ax", "bx");
        Assert.assertEquals(memory.getAx().getValue(), 0);

        arithmeticService.add("cx", "dx");
        Assert.assertEquals(memory.getCx().getValue(), 0);

        memory.getAx().setValue(0x0001);
        memory.getBx().setValue(0xFFFF);
        memory.getCx().setValue(0x0001);
        memory.getDx().setValue(0xFFFF);

        arithmeticService.add("bx", "ax");
        Assert.assertEquals(memory.getBx().getValue(), 0);

        arithmeticService.add("dx", "cx");
        Assert.assertEquals(memory.getDx().getValue(), 0);
    }
}
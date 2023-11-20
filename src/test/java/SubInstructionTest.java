import BB.memory.Memory;
import BB.service.ArithmeticService;
import org.junit.Assert;
import org.junit.Test;

public class SubInstructionTest {
    @Test
    public void subRegisterFromRegisterTest(){
        Memory memory = new Memory();
        ArithmeticService arithmeticService = new ArithmeticService(memory);

        memory.getAx().setValue(15);
        memory.getBx().setValue(6);
        memory.getCx().setValue(3);
        memory.getDx().setValue(9);

        arithmeticService.sub("ax", "bx");
        Assert.assertEquals(memory.getAx().getValue(), 9);

        arithmeticService.sub("bx", "cx");
        Assert.assertEquals(memory.getBx().getValue(), 3);

        arithmeticService.sub("ax", "bx");
        Assert.assertEquals(memory.getAx().getValue(), 6);

        arithmeticService.sub("dx", "ax");
        Assert.assertEquals(memory.getDx().getValue(), 3);

        arithmeticService.sub("cx", "dx");
        Assert.assertEquals(memory.getCx().getValue(), 0);
    }

    @Test
    public void subValueFromRegisterTest(){
        Memory memory = new Memory();
        ArithmeticService arithmeticService = new ArithmeticService(memory);

        memory.getAx().setValue(100);
        memory.getBx().setValue(100);
        memory.getCx().setValue(100);
        memory.getDx().setValue(100);

        arithmeticService.sub("ax", 5);
        Assert.assertEquals(memory.getAx().getValue(), 95);

        arithmeticService.sub("bx", 10);
        Assert.assertEquals(memory.getBx().getValue(), 90);

        arithmeticService.sub("cx", 15);
        Assert.assertEquals(memory.getCx().getValue(), 85);

        arithmeticService.sub("dx", 20);
        Assert.assertEquals(memory.getDx().getValue(), 80);

        arithmeticService.sub("ax", 10);
        Assert.assertEquals(memory.getAx().getValue(), 85);

        arithmeticService.sub("bx", 20);
        Assert.assertEquals(memory.getBx().getValue(), 70);

        arithmeticService.sub("cx", 5);
        Assert.assertEquals(memory.getCx().getValue(), 80);

        arithmeticService.sub("dx", 15);
        Assert.assertEquals(memory.getDx().getValue(), 65);
    }

    @Test
    public void subRegisterFromRegisterWithCarryTest(){
        Memory memory = new Memory();
        ArithmeticService arithmeticService = new ArithmeticService(memory);

        memory.getAx().setValue(0);
        memory.getBx().setValue(1);
        memory.getCx().setValue(0);
        memory.getDx().setValue(1);

        arithmeticService.sub("ax", "bx");
        Assert.assertEquals(memory.getAx().getValue(), 0xFFFF);

        arithmeticService.sub("cx", "dx");
        Assert.assertEquals(memory.getCx().getValue(), 0xFFFF);

        memory.getAx().setValue(1);
        memory.getBx().setValue(0);
        memory.getCx().setValue(1);
        memory.getDx().setValue(0);

        arithmeticService.sub("bx", "ax");
        Assert.assertEquals(memory.getBx().getValue(), 0xFFFF);

        arithmeticService.sub("dx", "cx");
        Assert.assertEquals(memory.getDx().getValue(), 0xFFFF);
    }

    @Test
    public void subValueFromRegisterWithCarryTest(){
        Memory memory = new Memory();
        ArithmeticService arithmeticService = new ArithmeticService(memory);

        memory.getAx().setValue(50);
        memory.getBx().setValue(50);
        memory.getCx().setValue(50);
        memory.getDx().setValue(50);

        arithmeticService.sub("ax", 51);
        Assert.assertEquals(memory.getAx().getValue(), 0xFFFF);

        arithmeticService.sub("bx", 51);
        Assert.assertEquals(memory.getBx().getValue(), 0xFFFF);

        arithmeticService.sub("cx", 51);
        Assert.assertEquals(memory.getCx().getValue(), 0xFFFF);

        arithmeticService.sub("dx", 51);
        Assert.assertEquals(memory.getDx().getValue(), 0xFFFF);
    }
}

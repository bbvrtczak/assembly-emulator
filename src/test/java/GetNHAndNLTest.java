import BB.memory.Memory;
import org.junit.Assert;
import org.junit.Test;

public class GetNHAndNLTest {
    @Test
    public void GetNHTest(){
        Memory memory = new Memory();

        memory.getAx().setValue(0x00FF);
        memory.getBx().setValue(0x0FF0);
        memory.getCx().setValue(0xFF00);
        memory.getDx().setValue(0xF00F);

        // last 8 bits of number actually matters (getting 8-bit register)
        Assert.assertEquals(memory.getAx().getNH(), 0x0000);
        Assert.assertEquals(memory.getBx().getNH(), 0x000F);
        Assert.assertEquals(memory.getCx().getNH(), 0x00FF);
        Assert.assertEquals(memory.getDx().getNH(), 0x00F0);
    }

    @Test
    public void GetNLTest(){
        Memory memory = new Memory();

        memory.getAx().setValue(0x00FF);
        memory.getBx().setValue(0x0FF0);
        memory.getCx().setValue(0xFF00);
        memory.getDx().setValue(0xF00F);

        // last 8 bits of number actually matters (getting 8-bit register)
        Assert.assertEquals(memory.getAx().getNL(), 0x00FF);
        Assert.assertEquals(memory.getBx().getNL(), 0x00F0);
        Assert.assertEquals(memory.getCx().getNL(), 0x0000);
        Assert.assertEquals(memory.getDx().getNL(), 0x000F);
    }
}

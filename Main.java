import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import me.Divine.utils.*;
import me.Divine.*;

@Mod(modid = "divine", version = "b0.1")
public class Main
{
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        if (MTSQL.isOnBase()) {
            Divine.instance = new Divine();
        }
        else {
            MTSQL.shotdown();
        }
        Divine.instance.init();
    }
}

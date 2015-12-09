package Example.plugin

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.sql.Time
import java.time.Instant
import java.util.logging.Logger

/**
 * Created by Mr.Whalerus on 12/8/2015.
 *
 */

public class Main() : JavaPlugin() , Listener, CommandExecutor {
    //init is called as soon as Main is loaded!
    init {
        print("This got called before everything else!")
    }
    //There is essentially no static in Kotlin, this is a list of vars(Read write objects) or vals(Read only objs) that are referenced by class.Companion.VARHERE !
    companion object{
        public val logger : Logger = Bukkit.getLogger();
    }
    //There is no need for the @Override annotation
    override fun onEnable() {
        logger.info { "Example plugin started!" }
        Bukkit.getPluginManager().registerEvents(this,this);
        Bukkit.getScheduler().runTaskTimer(this, RunTest(), 20 * 30, 20 * 30)

    }

    override fun onDisable() {
        logger.info { "Example plugin stopped!" }
    }

    @EventHandler
    public fun exampleEvent(event: PlayerJoinEvent){
        event.player.sendMessage("Hello from kotlin!");
    }

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {
        if (sender is Player){
            //Kotlin casts automatically using smart-casts!
            var p : Player = sender;
            //there is no switch in kotlin!!!
            when(command?.name){
                "testcmd" -> {
                    p.sendMessage("Hello from kotlin " + p.name + "!" )
                    p.sendMessage("Have a cake !")
                    p.inventory.addItem(ItemStack(Material.CAKE_BLOCK))
                    return true
                }

                else -> {
                    p.sendMessage("Command not found!")
                    return true
                }
            }


        } else {
            sender?.sendMessage("Your not a player! ")
            return true
        }

    }

    public fun RunTest() : Runnable {
        var r : Runnable = Runnable {
            Bukkit.broadcastMessage("Runnable ran successfully at "+ Time.from(Instant.now()))
        }
        return r;
    }
}
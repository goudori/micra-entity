package plugin.micra_eighteen;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AllSetLevelCommand implements CommandExecutor {

  public AllSetLevelCommand() {
  }

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s,
      String[] strings) {
    if (commandSender instanceof Player player) {
      player.sendMessage("実行できません。");
    } else {
      for (Player player : commandSender.getServer().getOnlinePlayers()) {
        player.setLevel(Integer.parseInt(strings[0]));
        System.out.println("プレイヤーレベルが" + strings[0] + "に設定されました。");
      }
    }
    return false;
  }
}
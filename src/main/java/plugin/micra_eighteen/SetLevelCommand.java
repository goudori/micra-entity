package plugin.micra_eighteen;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//  CommandExecutor　→コマンド実行する
//  CommandExecutorを作った後に、onCommandを作る事
// プレイヤーレベルを上げるコマンド
public class SetLevelCommand implements CommandExecutor {

  private Main main;


  public SetLevelCommand(Main main) {
    this.main = main;


  }


  //  strings →/コマンド　引数(マイクラで使うコマンドの事)
  @Override
  public boolean onCommand(CommandSender sender, Command command, String s,
      String[] strings) {
    if (sender instanceof Player player) {
      if (strings.length == 1) {
        player.setLevel(Integer.parseInt(strings[0]));

      } else {
//        config.ymlを読み込む
        player.sendMessage(main.getConfig().getString("Message"));
      }
    }
    return false;
  }
}
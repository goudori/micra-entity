package plugin.micra_eighteen;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin implements Listener {

  private int count = 0;

  @Override
  public void onEnable() {
//    config.ymlのファイルを自動的につくってくれる
    saveDefaultConfig();
    getConfig().getString("Message");

    Bukkit.getPluginManager().registerEvents(this, this);
    // setLevelコマンドを打つとSetLevelUpCommand.javaファイルにあるonCommandを実行する
    getCommand("setLevel").setExecutor(new SetLevelCommand(this));
    getCommand("allSetLevel").setExecutor(new AllSetLevelCommand());
  }

  //  プレイヤーがサーバーに参加した時のイベント
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    Player player = e.getPlayer();
    World world = player.getWorld();
//    エンティティ
//    プレイヤーの位置を取得
    Location playerLocation = player.getLocation();

//    カエル(🐸)を召喚
    world.spawn(
        new Location(world, playerLocation.getX() + 3, playerLocation.getY(),
            playerLocation.getZ()),
        Frog.class);

    String title = "エンティティ";
    player.sendTitle(title, "", 10, 70, 20);
  }


  /**
   * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
   *
   * @param e イベント
   */
  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
    // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
    Player player = e.getPlayer();
    World world = player.getWorld();

    // 大量の花火を繰り返し発生させる
    List<Color> colorList = List.of(Color.BLUE, Color.RED, Color.SILVER, Color.GRAY, Color.YELLOW);
    if (count % 2 == 0) {
      for (Color color : colorList) {
        // 花火オブジェクトをプレイヤーのロケーション地点に対して出現させる。
        Firework firework = world.spawn(player.getLocation(), Firework.class);

        // 花火オブジェクトが持つメタ情報を取得。
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        // メタ情報に対して設定を追加したり、値の上書きを行う。
        // 今回は青色で星型の花火を打ち上げる。
        fireworkMeta.addEffect(
            FireworkEffect.builder()
                .withColor(color)
                .with(Type.STAR)
                .withFlicker()
                .build());
        fireworkMeta.setPower(0);

        // 追加した情報で再設定する。
        firework.setFireworkMeta(fireworkMeta);
      }
    }

    count++;
  }
}
package me.niresed.Commands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;


public class RtpUtils {
    private static Location location;
    private static Player player;

    // запрещённые блоки
    private static final HashSet<Material> badBlocks = new HashSet<>();

    static {
        badBlocks.add(Material.LAVA);
        badBlocks.add(Material.WATER);
        badBlocks.add(Material.OAK_LEAVES);
        badBlocks.add(Material.SPRUCE_LEAVES);
        badBlocks.add(Material.JUNGLE_LEAVES);
        badBlocks.add(Material.ACACIA_LEAVES);
        badBlocks.add(Material.DARK_OAK_LEAVES);
        badBlocks.add(Material.COBWEB);
        badBlocks.add(Material.CACTUS);
        badBlocks.add(Material.SWEET_BERRY_BUSH);
        badBlocks.add(Material.CAMPFIRE);
        badBlocks.add(Material.MAGMA_BLOCK);
        badBlocks.add(Material.FIRE);
    }

    // генерация координаты
    public static Location generateLocation(Player thisPlayer) {
        player = thisPlayer;
        long m = System.currentTimeMillis();
        location = generatingRandomCoordinates();
        while (true) {
            boolean checkLocation = isLocationDeserted();
            boolean checkTerritory = isTerritoryFree();

            if (!checkLocation && !checkTerritory) {
                location = generatingRandomCoordinates();
            } else {
                break;
            }
        }
        System.out.print((System.currentTimeMillis() - m) / 1000f);
        System.out.println("Seconds");
        return location;
    }

    private static Location generatingRandomCoordinates() {
        Random random = new Random();
        int x = random.nextInt(1000);
        int y = 0;
        int z = random.nextInt(1000);

        location = new Location(player.getWorld(), x, y, z);
        y = location.getWorld().getHighestBlockYAt(location) + 1;
        location.setY(y);
        while (!isLocationSafe()) {
            x = random.nextInt(1000);
            y = 0;
            z = random.nextInt(1000);

            location = new Location(player.getWorld(), x, y, z);
            y = location.getWorld().getHighestBlockYAt(location) + 1;
            location.setY(y);
        }
        return location;
    }

    // смотрит если у точки есть запрещённый блок, который создал generateLocation(player)
    private static boolean isLocationSafe() {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);
        return !(badBlocks.contains(below.getType()) || (block.getType().isSolid()) || (above.getType().isSolid()));
    }

    // смотрит место безлюдное или нет
    private static boolean isLocationDeserted() {
        // a, b, c - стороны
        double hypotenuse;
        for (Player randomPlayer : Bukkit.getOnlinePlayers()) {
            if (randomPlayer.equals(player)) {
                continue;
            }

            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();

            double playerPositionX = randomPlayer.getLocation().getX();
            double playerPositionY = randomPlayer.getLocation().getY();
            double playerPositionZ = randomPlayer.getLocation().getZ();

            double a = playerPositionX - x;
            double b = playerPositionZ - z;
            double c = playerPositionY - y;

            hypotenuse = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));
            if (hypotenuse < 150) {
                return false;
            }
        }
        return true;
    }
//s
    // смотрит свободная ли территория
    private static boolean isTerritoryFree() {
        if (TownyAPI.getInstance().isWilderness(location)) {
            return true;
        }  else {
            Town town = TownyAPI.getInstance().getTown(player.getLocation());
            if (town != null && town.hasResident(player.getName())) {
                Bukkit.getLogger().info("the player is resident");
            }
        }
        return TownyAPI.getInstance().isWilderness(location);
    }
}
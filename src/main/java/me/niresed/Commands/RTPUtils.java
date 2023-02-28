package me.niresed.Commands;

import com.palmergames.bukkit.towny.TownyAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;


public class RTPUtils {
    // запрещённые блоки
    public static HashSet<Material> bad_blocks = new HashSet<>();
    static {
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.WATER);
        bad_blocks.add(Material.OAK_LEAVES);
        bad_blocks.add(Material.SPRUCE_LEAVES);
        bad_blocks.add(Material.JUNGLE_LEAVES);
        bad_blocks.add(Material.ACACIA_LEAVES);
        bad_blocks.add(Material.DARK_OAK_LEAVES);
        bad_blocks.add(Material.COBWEB);
        bad_blocks.add(Material.CACTUS);
        bad_blocks.add(Material.SWEET_BERRY_BUSH);
        bad_blocks.add(Material.CAMPFIRE);
        bad_blocks.add(Material.MAGMA_BLOCK);
        bad_blocks.add(Material.FIRE);
    }

    // генерация кординатов
    public static Location generateLocation(Player player){
        long m = System.currentTimeMillis();
        Location randomLocation = generatingRandomCoordinates(player);
        boolean s = true;
        while (s) {
            boolean check_location = isLocationDeserted(randomLocation, player);
            boolean check_territory = isTerritoryFree(randomLocation);
            if (!check_location && !check_territory){
                randomLocation = generatingRandomCoordinates(player);
            } else{
                s = false;
            }
        }
        System.out.print((System.currentTimeMillis() - m) / 1000f);
        System.out.println("Seconds");
        return randomLocation;
    }
    private static Location generatingRandomCoordinates(Player player){
        Random random = new Random();
        int x = random.nextInt(1000);
        int y = 0;
        int z = random.nextInt(1000);

        Location randomLocation = new Location(player.getWorld(), x, y, z);
        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation) + 1;
        randomLocation.setY(y);
        while (!isLocationSafe(randomLocation)){
            x = random.nextInt(1000);
            y = 0;
            z = random.nextInt(1000);

            randomLocation = new Location(player.getWorld(), x, y, z);
            y = randomLocation.getWorld().getHighestBlockYAt(randomLocation) + 1;
            randomLocation.setY(y);
        }
        return randomLocation;
    }

    // смотрит если у точки кординатов есть запрещённый блок который создал generateLocation(player)
    private static boolean isLocationSafe(Location location){
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);
        return !(bad_blocks.contains(below.getType()) || (block.getType().isSolid()) || (above.getType().isSolid()));
    }

    // смотрит место безлюдное или нет
    private static boolean isLocationDeserted(Location location, Player player_1){
        double h;
        for (Player player:Bukkit.getOnlinePlayers()) {
            if (player_1.equals(player)){
                continue;
            }

            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();

            double playerPositionX = player.getLocation().getX();
            double playerPositionY = player.getLocation().getY();
            double playerPositionZ = player.getLocation().getZ();

            double a = playerPositionX - x;
            double b = playerPositionZ - z;
            double height = playerPositionY - y;

            h = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(height, 2));

            if (h < 150){
                return false;
            }
        }
        return true;
    }
    // смотрит свободная ли территория
    private static boolean isTerritoryFree(Location location) {
        if(TownyAPI.getInstance().isWilderness(location))
            return true;
        return false;
    }
}

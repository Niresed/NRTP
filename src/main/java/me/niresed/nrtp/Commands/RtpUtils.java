package me.niresed.nrtp.Commands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.WorldCoord;
import me.niresed.nrtp.NRTP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;


public class RtpUtils {
    private static final Plugin plugin = NRTP.getPlugin(NRTP.class);
    private static final ArrayList<Integer> coordinate = (ArrayList<Integer>) plugin.getConfig().getIntegerList("random teleport region");
    private static Player player;
    // запрещённые блоки
    public static HashSet<Material> badBlocks = new HashSet<>();

    static {
        badBlocks.add(Material.LAVA);
        badBlocks.add(Material.WATER);
        badBlocks.add(Material.OAK_LEAVES);
        badBlocks.add(Material.SPRUCE_LEAVES);
        badBlocks.add(Material.JUNGLE_LEAVES);
        badBlocks.add(Material.ACACIA_LEAVES);
        badBlocks.add(Material.DARK_OAK_LEAVES);
        badBlocks.add(Material.BIRCH_LEAVES);
        badBlocks.add(Material.COBWEB);
        badBlocks.add(Material.CACTUS);
        badBlocks.add(Material.SWEET_BERRY_BUSH);
        badBlocks.add(Material.CAMPFIRE);
        badBlocks.add(Material.MAGMA_BLOCK);
        badBlocks.add(Material.FIRE);
        badBlocks.add(Material.MUSHROOM_STEM);
        badBlocks.add(Material.MUSHROOM_STEW);
        badBlocks.add(Material.KELP);
    }

    // генерация координаты
    public static Location generateLocation(Player thisPlayer) {
        player = thisPlayer;
        long startMinute = System.currentTimeMillis();
        Location location = generatingRandomCoordinates();
        while (true) {
            boolean checkLocation = isLocationDeserted(location);
            boolean checkTerritory = isTerritoryFree(location);
            boolean checkGeneration = isLocationSafe(location);
            if (!checkLocation || !checkTerritory || !checkGeneration) {
                location = generatingRandomCoordinates();
            } else {
                break;
            }
        }
        double finishMinute = (System.currentTimeMillis() - startMinute) / 1000d;
        Bukkit.getLogger().info(String.format("%f Seconds %n", finishMinute));
        location.setX(location.getX() + 0.5f);
        location.setZ(location.getZ() + 0.5f);
        return location;
    }

    private static Location generatingRandomCoordinates() {
        Random random = new Random();
        int minX = coordinate.get(0), minZ = coordinate.get(1);
        int maxX = coordinate.get(2), maxZ = coordinate.get(3);
        int x = random.nextInt((maxX - minX + 1) + minX);
        int y = 0;
        int z = random.nextInt((maxZ - minZ + 1) + minZ);

        Location location = new Location(player.getWorld(), x, y, z);
        y = location.getWorld().getHighestBlockYAt(location) + 1;
        location.setY(y);
        return location;
    }

    // смотрит если у точки есть запрещённый блок, который создал generateLocation(player)
    private static boolean isLocationSafe(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);
        return !(badBlocks.contains(below.getType()) || (block.getType().isSolid()) || (above.getType().isSolid()));
    }

    // смотрит место безлюдное или нет
    private static boolean isLocationDeserted(Location location) {
        for (Player randomPlayer: location.getNearbyPlayers(100)) {
            if (randomPlayer.equals(player)) {
                continue;
            }
            return false;
        }
        return true;
    }

    // смотрит свободная ли территория
    private static boolean isTerritoryFree(Location location) {
        double positionX = location.getX();
        double positionZ = location.getZ();
        double x = -64;
        double z = -64;
        double minZ = -48;
        double minX = -64;
        double maxZ = 64;
        double maxX = 64;
        location.clone().setZ(positionZ + z);
        location.clone().setX(positionX + x);
        if (!TownyAPI.getInstance().isWilderness(location)) {
            return false;
        }
        for (int i = 0; i < 5; i++){
            if (i == 4){
                location.clone().setZ(positionZ);
                location.clone().setX(positionX);
                if (!TownyAPI.getInstance().isWilderness(location)) {
                    return false;
                }
                break;
            }
            // слева
            while (x < maxX) {
                x += 16;
                location.clone().setX(positionX + x);
                if (!TownyAPI.getInstance().isWilderness(location)) {
                    return false;
                }

            }
            // сверху
            while (z < maxZ) {
                z += 16;
                location.clone().setZ(positionZ + z);
                if (!TownyAPI.getInstance().isWilderness(location)) {
                    return false;
                }
            }
            // справа
            while (x > minX) {
                x -= 16;
                location.clone().setX(positionX + x);
                if (!TownyAPI.getInstance().isWilderness(location)) {
                    return false;
                }
            }
            // снизу
            while (z > minZ) {
                z -= 16;
                location.clone().setZ(positionZ + z);
                if (!TownyAPI.getInstance().isWilderness(location)) {
                    return false;
                }
            }
            maxX -= 16;
            minX += 16;
            maxZ -= 16;
            minZ += 16;
        }
        return true;
    }
}
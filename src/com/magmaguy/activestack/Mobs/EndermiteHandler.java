package com.magmaguy.activestack.Mobs;

import com.magmaguy.activestack.ActiveStack;
import com.magmaguy.activestack.MobScanner;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

import java.util.Random;

/**
 * Created by MagmaGuy on 08/10/2016.
 */
public class EndermiteHandler implements Listener{

    private ActiveStack plugin;

    public EndermiteHandler (Plugin plugin){

        this.plugin = (ActiveStack) plugin;

    }


    @EventHandler
    public void onHit(EntityDamageEvent event) {

        if (event.getEntity() instanceof Endermite && event.getEntity().hasMetadata("SuperMob"))
        {

            Random random = new Random();

            Endermite endermite = (Endermite) event.getEntity();

            double damage = event.getFinalDamage();
            //health is hardcoded here, maybe change it at some point
            double dropChance = damage / MobScanner.endermiteHealth;
            double dropRandomizer = random.nextDouble();
            //this rounds down
            int dropMinAmount = (int) dropChance;

            //there are no drops for this mob, XP aside

            for (int i = 0; i < dropMinAmount; i++)
            {

                ExperienceOrb xpDrop = endermite.getWorld().spawn(endermite.getLocation(), ExperienceOrb.class);
                xpDrop.setExperience(3);

            }

            if (dropChance > dropRandomizer)
            {

                ExperienceOrb xpDrop = endermite.getWorld().spawn(endermite.getLocation(), ExperienceOrb.class);
                xpDrop.setExperience(3);

            }

        }

    }


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){

        if (event.getDamager() instanceof Endermite && event.getDamager().hasMetadata("SuperMob"))
        {

            event.setDamage(event.getFinalDamage() * event.getDamager().getMetadata("SuperMob").get(0).asInt());

        }

    }

}
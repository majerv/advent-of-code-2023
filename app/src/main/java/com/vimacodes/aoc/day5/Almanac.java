package com.vimacodes.aoc.day5;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class Almanac {

  List<Item> seeds;
  PlantingInstructions seedsToSoil;
  PlantingInstructions soilToFertilizer;
  PlantingInstructions fertilizerToWater;
  PlantingInstructions waterToLight;
  PlantingInstructions lightToTemperature;
  PlantingInstructions temperatureToHumidity;
  PlantingInstructions humidityToLocation;

  public static Almanac parse(String text) {
    List<String> lines = text.lines().toList();
    List<Item> theSeeds = parseSeeds(lines.get(0));

    AlmanacBuilder almanacBuilder = Almanac.builder().seeds(theSeeds);

    for (int i = 1; i < lines.size(); i++) {
      String line = lines.get(i);
      if (line.isBlank()) continue;

      // TODO make it generic... :)
      if (line.startsWith("seed-to-soil")) {
        var seedsToSoil = parseInstructions(ItemType.SEED, ItemType.SOIL, lines, i + 1);
        almanacBuilder.seedsToSoil(seedsToSoil);
        i += seedsToSoil.size();
      }

      if (line.startsWith("soil-to-fertilizer")) {
        var parsed = parseInstructions(ItemType.SOIL, ItemType.FERTILIZER, lines, i + 1);
        almanacBuilder.soilToFertilizer(parsed);
        i += parsed.size();
      }

      if (line.startsWith("fertilizer-to-water")) {
        var parsed = parseInstructions(ItemType.FERTILIZER, ItemType.WATER, lines, i + 1);
        almanacBuilder.fertilizerToWater(parsed);
        i += parsed.size();
      }

      if (line.startsWith("water-to-light")) {
        var parsed = parseInstructions(ItemType.WATER, ItemType.LIGHT, lines, i + 1);
        almanacBuilder.waterToLight(parsed);
        i += parsed.size();
      }

      if (line.startsWith("light-to-temperature")) {
        var parsed = parseInstructions(ItemType.LIGHT, ItemType.TEMPERATURE, lines, i + 1);
        almanacBuilder.lightToTemperature(parsed);
        i += parsed.size();
      }

      if (line.startsWith("temperature-to-humidity")) {
        var parsed = parseInstructions(ItemType.TEMPERATURE, ItemType.HUMIDITY, lines, i + 1);
        almanacBuilder.temperatureToHumidity(parsed);
        i += parsed.size();
      }

      if (line.startsWith("humidity-to-location")) {
        var parsed = parseInstructions(ItemType.HUMIDITY, ItemType.LOCATION, lines, i + 1);
        almanacBuilder.humidityToLocation(parsed);
        i += parsed.size();
      }
    }

    Almanac almanac = almanacBuilder.build();
    System.out.println("Almanac: \n" + almanac);
    return almanac;
  }

  private static PlantingInstructions parseInstructions(
      ItemType from, ItemType to, List<String> lines, int startIndex) {
    Map<Long, PlantMapping> instructions = new HashMap<>();
    String line = lines.get(startIndex);
    while (startIndex < lines.size() && !line.isBlank()) {
      line = lines.get(startIndex);
      System.out.println("Parsing: " + line);
      if (!line.isBlank()) {
        String[] parts = line.split("\\s+");
        long source = Long.parseLong(parts[1]);
        long destination = Long.parseLong(parts[0]);
        long rangeLength = Long.parseLong(parts[2]);

        instructions.put(source, new PlantMapping(source, destination, rangeLength));
      }
      ++startIndex;
    }

    return PlantingInstructions.builder().from(from).to(to).instructions(instructions).build();
  }

  private static List<Item> parseSeeds(String line) {
    String s = line.split(":")[1].stripLeading().stripTrailing();
    return Arrays.stream(s.split("\\s+"))
        .map(Long::parseLong)
        .map(i -> new Item(ItemType.SEED, i))
        .toList();
  }

  public List<Item> seeds() {
    return seeds;
  }

  public PlantingPlan getPlantingPlan(Item seed) {
    Preconditions.checkArgument(seed.getType() == ItemType.SEED);
    //    Item location = calculateLocation(seed);

    long soilId = seedsToSoil.getDestination(seed);
    long fertilizerId = soilToFertilizer.getDestination(soilId);
    long waterId = fertilizerToWater.getDestination(fertilizerId);
    long lightId = waterToLight.getDestination(waterId);
    long temperatureId = lightToTemperature.getDestination(lightId);
    long humidityId = temperatureToHumidity.getDestination(temperatureId);
    long locationId = humidityToLocation.getDestination(humidityId);

    PlantingPlan plantingPlan =
        PlantingPlan.builder()
            .seed(seed)
            .soil(new Item(ItemType.SOIL, soilId))
            .fertilizer(new Item(ItemType.FERTILIZER, fertilizerId))
            .water(new Item(ItemType.WATER, waterId))
            .light(new Item(ItemType.LIGHT, lightId))
            .temperature(new Item(ItemType.TEMPERATURE, temperatureId))
            .humidity(new Item(ItemType.HUMIDITY, humidityId))
            .location(new Item(ItemType.LOCATION, locationId))
            .build();

    System.out.println(plantingPlan);
    return plantingPlan;
  }
}

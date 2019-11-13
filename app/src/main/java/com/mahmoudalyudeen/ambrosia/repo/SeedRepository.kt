package com.mahmoudalyudeen.ambrosia.repo

import com.mahmoudalyudeen.ambrosia.db.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SeedRepository(private val foodDatabase: FoodDatabase) {

    fun initializeData() {

        val apple = DatabaseProduct(1, "Apples")
        val carrot = DatabaseProduct(2, "Carrots")
        val potato = DatabaseProduct(3, "Potatoes")
        val milk = DatabaseProduct(4, "Milk")
        val egg = DatabaseProduct(5, "Egg, boiled")
        val banana = DatabaseProduct(6, "Bananas")
        val yogurt = DatabaseProduct(7, "Yogurt")
        val chicken = DatabaseProduct(8, "Chicken")
        val orangeJuice = DatabaseProduct(9, "Orange juice")
        val grapefruitJuice = DatabaseProduct(10, "Grapefruit juice")
        val zucchini = DatabaseProduct(11, "Zucchini")
        val pea = DatabaseProduct(12, "Peas")
        val tomato = DatabaseProduct(13, "Tomatoes")
        val cucumber = DatabaseProduct(14, "Cucumbers")
        val lettuce = DatabaseProduct(15, "Lettuce")
        val rice = DatabaseProduct(16, "Rice")
        val pasta = DatabaseProduct(17, "Pasta")
        val spinach = DatabaseProduct(18, "Spinach")
        val pear = DatabaseProduct(19, "Pears")
        val guava = DatabaseProduct(20, "Guavas")

        val energy = DatabaseNutrient(1, "Energy", "cal", 850.0, "Approximates", true)
        val carbs = DatabaseNutrient(2, "Carbohydrates", "g", 95.0, "Approximates", true)
        val protein = DatabaseNutrient(3, "Protein", "g", 38.0, "Approximates", true)
        val sugars = DatabaseNutrient(4, "Sugars", "g", 10.0, "Approximates", false)
        val vitaminA = DatabaseNutrient(5, "Vitamin A", "iu", 1500.0, "Vitamins", false)
        val vitaminB = DatabaseNutrient(6, "Vitamin B", "µg", 20.0, "Vitamins", false)
        val vitaminC = DatabaseNutrient(7, "Vitamin C", "mg", 400.0, "Vitamins", false)
        val vitaminD = DatabaseNutrient(8, "Vitamin D", "IU", 400.0, "Vitamins", false)
        val vitaminE = DatabaseNutrient(9, "Vitamin E", "mg", 6.0, "Vitamins", false)
        val vitaminK = DatabaseNutrient(10, "Vitamin K", "µg", 2.0, "Vitamins", false)
        val potassium = DatabaseNutrient(11, "Potassium", "mg", 3000.0, "Minerals", false)
        val sodium = DatabaseNutrient(12, "Sodium", "mg", 1500.0, "Minerals", false)
        val calcium = DatabaseNutrient(13, "Calcium", "mg", 700.0, "Minerals", false)
        val magnesium = DatabaseNutrient(14, "Magnesium", "mg", 100.0, "Minerals", false)
        val phosphorus = DatabaseNutrient(15, "Phosphorus", "mg", 300.0, "Minerals", false)
        val zinc = DatabaseNutrient(16, "Zinc", "mg", 5.0, "Minerals", false)

        val potatoEnergy = ProductNutrientJoin(potato.productId, energy.nutrientId, 1.0)
        val potatoCarbs = ProductNutrientJoin(potato.productId, carbs.nutrientId, 0.2)
        val potatoSugars = ProductNutrientJoin(potato.productId, sugars.nutrientId, 0.01)
        val potatoPotassium = ProductNutrientJoin(potato.productId, potassium.nutrientId, 3.0)
        val potatoProtein = ProductNutrientJoin(potato.productId, protein.nutrientId, 0.02)
        val potatoVitaminC = ProductNutrientJoin(potato.productId, vitaminC.nutrientId, 0.1)

        val carrotEnergy = ProductNutrientJoin(carrot.productId, energy.nutrientId, 0.40)
        val carrotCarbs = ProductNutrientJoin(carrot.productId, carbs.nutrientId, 0.1)
        val carrotSugars = ProductNutrientJoin(carrot.productId, sugars.nutrientId, 0.05)
        val carrotProtein = ProductNutrientJoin(carrot.productId, protein.nutrientId, 0.01)
        val carrotVitaminA = ProductNutrientJoin(carrot.productId, vitaminA.nutrientId, 167.0)
        val carrotPotassium = ProductNutrientJoin(carrot.productId, potassium.nutrientId, 3.0)
        val carrotVitaminC = ProductNutrientJoin(carrot.productId, vitaminC.nutrientId, 0.1)
        val carrotVitaminE = ProductNutrientJoin(carrot.productId, vitaminE.nutrientId, 0.01)
        val carrotVitaminK = ProductNutrientJoin(carrot.productId, vitaminK.nutrientId, 0.1)

        val appleEnergy = ProductNutrientJoin(apple.productId, energy.nutrientId, 1.0)
        val appleProtein = ProductNutrientJoin(apple.productId, protein.nutrientId, 0.0)
        val appleCarbs = ProductNutrientJoin(apple.productId, carbs.nutrientId, 0.14)
        val appleSugars = ProductNutrientJoin(apple.productId, sugars.nutrientId, 0.1)
        val applePotassium = ProductNutrientJoin(apple.productId, potassium.nutrientId, 1.0)
        val appleVitaminA = ProductNutrientJoin(apple.productId, vitaminA.nutrientId, 1.0)

        val milkEnergy = ProductNutrientJoin(milk.productId, energy.nutrientId, 0.35)
        val milkProtein = ProductNutrientJoin(milk.productId, protein.nutrientId, 0.03)
        val milkCarbs = ProductNutrientJoin(milk.productId, carbs.nutrientId, 0.04)
        val milkSugars = ProductNutrientJoin(milk.productId, sugars.nutrientId, 0.48)
        val milkCalcium = ProductNutrientJoin(milk.productId, calcium.nutrientId, 2.04)
        val milkMagnesium = ProductNutrientJoin(milk.productId, magnesium.nutrientId, 0.11)
        val milkPhosphorus = ProductNutrientJoin(milk.productId, phosphorus.nutrientId, 1.01)
        val milkPotassium = ProductNutrientJoin(milk.productId, potassium.nutrientId, 1.66)
        val milkSodium = ProductNutrientJoin(milk.productId, sodium.nutrientId, 0.52)
        val milkVitaminA = ProductNutrientJoin(milk.productId, vitaminA.nutrientId, 4.58)
        val milkVitaminC = ProductNutrientJoin(milk.productId, vitaminC.nutrientId, 0.01)
        val milkVitaminD = ProductNutrientJoin(milk.productId, vitaminD.nutrientId, 0.01)

        val eggEnergy = ProductNutrientJoin(egg.productId, energy.nutrientId, 2.0)
        val eggProtein = ProductNutrientJoin(egg.productId, protein.nutrientId, 0.13)
        val eggCarbs = ProductNutrientJoin(egg.productId, carbs.nutrientId, 0.01)
        val eggSugars = ProductNutrientJoin(egg.productId, sugars.nutrientId, 0.01)
        val eggPhosphorus = ProductNutrientJoin(egg.productId, phosphorus.nutrientId, 2.0)
        val eggPotassium = ProductNutrientJoin(egg.productId, potassium.nutrientId, 1.0)
        val eggSodium = ProductNutrientJoin(egg.productId, sodium.nutrientId, 1.0)
        val eggZinc = ProductNutrientJoin(egg.productId, zinc.nutrientId, 0.01)
        val eggVitaminB = ProductNutrientJoin(egg.productId, vitaminB.nutrientId, 0.01)
        val eggVitaminA = ProductNutrientJoin(egg.productId, vitaminA.nutrientId, 5.0)
        val eggVitaminE = ProductNutrientJoin(egg.productId, vitaminE.nutrientId, 0.01)
        val eggVitaminD = ProductNutrientJoin(egg.productId, vitaminD.nutrientId, 1.0)

        val bananaEnergy = ProductNutrientJoin(banana.productId, energy.nutrientId, 1.0)
        val bananaProtein = ProductNutrientJoin(banana.productId, protein.nutrientId, 0.01)
        val bananaCarbs = ProductNutrientJoin(banana.productId, carbs.nutrientId, 0.23)
        val bananaSugars = ProductNutrientJoin(banana.productId, sugars.nutrientId, 0.12)
        val bananaPotassium = ProductNutrientJoin(banana.productId, potassium.nutrientId, 4.0)
        val bananaVitaminC = ProductNutrientJoin(banana.productId, vitaminC.nutrientId, 0.1)
        val bananaVitaminA = ProductNutrientJoin(banana.productId, vitaminA.nutrientId, 1.0)

        val yogurtEnergy = ProductNutrientJoin(yogurt.productId, energy.nutrientId, 1.0)
        val yogurtProtein = ProductNutrientJoin(yogurt.productId, protein.nutrientId, 0.05)
        val yogurtCarbs = ProductNutrientJoin(yogurt.productId, carbs.nutrientId, 0.08)
        val yogurtSugars = ProductNutrientJoin(yogurt.productId, sugars.nutrientId, 0.07)
        val yogurtCalcium = ProductNutrientJoin(yogurt.productId, calcium.nutrientId, 2.0)
        val yogurtPhosphorus = ProductNutrientJoin(yogurt.productId, phosphorus.nutrientId, 1.0)
        val yogurtPotassium = ProductNutrientJoin(yogurt.productId, potassium.nutrientId, 2.0)
        val yogurtSodium = ProductNutrientJoin(yogurt.productId, sodium.nutrientId, 1.0)

        val chickenEnergy = ProductNutrientJoin(chicken.productId, energy.nutrientId, 2.0)
        val chickenProtein = ProductNutrientJoin(chicken.productId, protein.nutrientId, 0.3)
        val chickenCarbs = ProductNutrientJoin(chicken.productId, carbs.nutrientId, 0.0)
        val chickenSugars = ProductNutrientJoin(chicken.productId, sugars.nutrientId, 0.0)
        val chickenPhosphorus = ProductNutrientJoin(chicken.productId, phosphorus.nutrientId, 2.0)
        val chickenPotassium = ProductNutrientJoin(chicken.productId, potassium.nutrientId, 2.0)
        val chickenSodium = ProductNutrientJoin(chicken.productId, sodium.nutrientId, 1.0)
        val chickenZinc = ProductNutrientJoin(chicken.productId, zinc.nutrientId, 0.02)
        val chickenVitaminA = ProductNutrientJoin(chicken.productId, vitaminA.nutrientId, 1.0)

        val orangeJuiceEnergy = ProductNutrientJoin(orangeJuice.productId, energy.nutrientId, 1.0)
        val orangeJuiceProtein = ProductNutrientJoin(orangeJuice.productId, protein.nutrientId, 0.03)
        val orangeJuiceCarbs = ProductNutrientJoin(orangeJuice.productId, carbs.nutrientId, 0.33)
        val orangeJuiceSugars = ProductNutrientJoin(orangeJuice.productId, sugars.nutrientId, 0.33)
        val orangeJuicePotassium = ProductNutrientJoin(orangeJuice.productId, potassium.nutrientId, 6.0)
        val orangeJuiceVitaminC = ProductNutrientJoin(orangeJuice.productId, vitaminC.nutrientId, 1.0)

        val grapefruitJuiceEnergy = ProductNutrientJoin(grapefruitJuice.productId, energy.nutrientId, 0.5)
        val grapefruitJuiceProtein = ProductNutrientJoin(grapefruitJuice.productId, protein.nutrientId, 0.0)
        val grapefruitJuiceCarbs = ProductNutrientJoin(grapefruitJuice.productId, carbs.nutrientId, 0.1)
        val grapefruitJuiceSugars = ProductNutrientJoin(grapefruitJuice.productId, sugars.nutrientId, 0.1)
        val grapefruitJuiceCalcium = ProductNutrientJoin(grapefruitJuice.productId, calcium.nutrientId, 0.08)
        val grapefruitJuicePotassium = ProductNutrientJoin(grapefruitJuice.productId, potassium.nutrientId, 2.0)
        val grapefruitJuiceVitaminC = ProductNutrientJoin(grapefruitJuice.productId, vitaminC.nutrientId, 0.25)

        val zucchiniEnergy = ProductNutrientJoin(zucchini.productId, energy.nutrientId, 0.17)
        val zucchiniProtein = ProductNutrientJoin(zucchini.productId, protein.nutrientId, 0.0)
        val zucchiniCarbs = ProductNutrientJoin(zucchini.productId, carbs.nutrientId, 0.0)
        val zucchiniSugars = ProductNutrientJoin(zucchini.productId, sugars.nutrientId, 0.0)
        val zucchiniCalcium = ProductNutrientJoin(zucchini.productId, calcium.nutrientId, 0.16)
        val zucchiniMagnesium = ProductNutrientJoin(zucchini.productId, magnesium.nutrientId, 0.18)
        val zucchiniPhosphorus = ProductNutrientJoin(zucchini.productId, phosphorus.nutrientId, 0.38)
        val zucchiniPotassium = ProductNutrientJoin(zucchini.productId, potassium.nutrientId, 2.61)
        val zucchiniSodium = ProductNutrientJoin(zucchini.productId, sodium.nutrientId, 0.08)
        val zucchiniVitaminC = ProductNutrientJoin(zucchini.productId, vitaminC.nutrientId, 0.18)
        val zucchiniVitaminA = ProductNutrientJoin(zucchini.productId, vitaminA.nutrientId, 2.0)
        val zucchiniVitaminK = ProductNutrientJoin(zucchini.productId, vitaminK.nutrientId, 0.04)

        val peasEnergy = ProductNutrientJoin(pea.productId, energy.nutrientId, 0.42)
        val peasProtein = ProductNutrientJoin(pea.productId, protein.nutrientId, 0.02)
        val peasCarbs = ProductNutrientJoin(pea.productId, carbs.nutrientId, 0.75)
        val peasSugars = ProductNutrientJoin(pea.productId, sugars.nutrientId, 0.04)
        val peasCalcium = ProductNutrientJoin(pea.productId, calcium.nutrientId, 0.43)
        val peasPhosphorus = ProductNutrientJoin(pea.productId, phosphorus.nutrientId, 0.53)
        val peasSodium = ProductNutrientJoin(pea.productId, sodium.nutrientId, 0.04)
        val peasVitaminC = ProductNutrientJoin(pea.productId, vitaminC.nutrientId, 0.6)
        val peasVitaminA = ProductNutrientJoin(pea.productId, vitaminA.nutrientId, 10.87)
        val peasVitaminK = ProductNutrientJoin(pea.productId, vitaminK.nutrientId, 0.25)

        val tomatoEnergy = ProductNutrientJoin(tomato.productId, energy.nutrientId, 0.18)
        val tomatoProtein = ProductNutrientJoin(tomato.productId, protein.nutrientId, 0.0)
        val tomatoCarbs = ProductNutrientJoin(tomato.productId, carbs.nutrientId, 0.38)
        val tomatoSugars = ProductNutrientJoin(tomato.productId, sugars.nutrientId, 0.26)
        val tomatoCalcium = ProductNutrientJoin(tomato.productId, calcium.nutrientId, 0.1)
        val tomatoMagnesium = ProductNutrientJoin(tomato.productId, magnesium.nutrientId, 0.11)
        val tomatoPhosphorus = ProductNutrientJoin(tomato.productId, phosphorus.nutrientId, 0.24)
        val tomatoPotassium = ProductNutrientJoin(tomato.productId, potassium.nutrientId, 2.37)
        val tomatoSodium = ProductNutrientJoin(tomato.productId, sodium.nutrientId, 0.05)
        val tomatoVitaminC = ProductNutrientJoin(tomato.productId, vitaminC.nutrientId, 0.13)
        val tomatoVitaminA = ProductNutrientJoin(tomato.productId, vitaminA.nutrientId, 8.33)
        val tomatoVitaminK = ProductNutrientJoin(tomato.productId, vitaminK.nutrientId, 0.79)

        val cucumberEnergy = ProductNutrientJoin(cucumber.productId, energy.nutrientId, 0.15)
        val cucumberProtein = ProductNutrientJoin(cucumber.productId, protein.nutrientId, 0.0)
        val cucumberCarbs = ProductNutrientJoin(cucumber.productId, carbs.nutrientId, 0.03)
        val cucumberSugars = ProductNutrientJoin(cucumber.productId, sugars.nutrientId, 0.01)
        val cucumberCalcium = ProductNutrientJoin(cucumber.productId, calcium.nutrientId, 0.16)
        val cucumberMagnesium = ProductNutrientJoin(cucumber.productId, magnesium.nutrientId, 0.13)
        val cucumberPhosphorus = ProductNutrientJoin(cucumber.productId, phosphorus.nutrientId, 0.24)
        val cucumberPotassium = ProductNutrientJoin(cucumber.productId, potassium.nutrientId, 1.47)
        val cucumberSodium = ProductNutrientJoin(cucumber.productId, sodium.nutrientId, 0.02)
        val cucumberVitaminC = ProductNutrientJoin(cucumber.productId, vitaminC.nutrientId, 0.02)
        val cucumberVitaminA = ProductNutrientJoin(cucumber.productId, vitaminA.nutrientId, 1.05)
        val cucumberVitaminK = ProductNutrientJoin(cucumber.productId, vitaminK.nutrientId, 0.16)

        val lettuceEnergy = ProductNutrientJoin(lettuce.productId, energy.nutrientId, 0.15)
        val lettuceProtein = ProductNutrientJoin(lettuce.productId, protein.nutrientId, 0.01)
        val lettuceCarbs = ProductNutrientJoin(lettuce.productId, carbs.nutrientId, 0.02)
        val lettuceSugars = ProductNutrientJoin(lettuce.productId, sugars.nutrientId, 0.0)
        val lettuceCalcium = ProductNutrientJoin(lettuce.productId, calcium.nutrientId, 0.36)
        val lettuceMagnesium = ProductNutrientJoin(lettuce.productId, magnesium.nutrientId, 0.13)
        val lettucePhosphorus = ProductNutrientJoin(lettuce.productId, phosphorus.nutrientId, 0.29)
        val lettucePotassium = ProductNutrientJoin(lettuce.productId, potassium.nutrientId, 1.94)
        val lettuceSodium = ProductNutrientJoin(lettuce.productId, sodium.nutrientId, 0.28)
        val lettuceVitaminC = ProductNutrientJoin(lettuce.productId, vitaminC.nutrientId, 0.09)
        val lettuceVitaminA = ProductNutrientJoin(lettuce.productId, vitaminA.nutrientId, 74.05)
        val lettuceVitaminK = ProductNutrientJoin(lettuce.productId, vitaminK.nutrientId, 1.26)

        val riceEnergy = ProductNutrientJoin(rice.productId, energy.nutrientId, 1.3)
        val riceProtein = ProductNutrientJoin(rice.productId, protein.nutrientId, 0.02)
        val riceCarbs = ProductNutrientJoin(rice.productId, carbs.nutrientId, 0.3)
        val riceSugars = ProductNutrientJoin(rice.productId, sugars.nutrientId, 0.0)
        val riceCalcium = ProductNutrientJoin(rice.productId, calcium.nutrientId, 0.1)
        val riceMagnesium = ProductNutrientJoin(rice.productId, magnesium.nutrientId, 0.1)
        val ricePhosphorus = ProductNutrientJoin(rice.productId, phosphorus.nutrientId, 0.43)
        val ricePotassium = ProductNutrientJoin(rice.productId, potassium.nutrientId, 0.35)
        val riceSodium = ProductNutrientJoin(rice.productId, sodium.nutrientId, 0.01)

        val pastaEnergy = ProductNutrientJoin(pasta.productId, energy.nutrientId, 1.3)
        val pastaProtein = ProductNutrientJoin(pasta.productId, protein.nutrientId, 0.05)
        val pastaCarbs = ProductNutrientJoin(pasta.productId, carbs.nutrientId, 0.24)
        val pastaSugars = ProductNutrientJoin(pasta.productId, sugars.nutrientId, 0.0)
        val pastaCalcium = ProductNutrientJoin(pasta.productId, calcium.nutrientId, 0.01)
        val pastaMagnesium = ProductNutrientJoin(pasta.productId, magnesium.nutrientId, 0.14)
        val pastaPhosphorus = ProductNutrientJoin(pasta.productId, phosphorus.nutrientId, 0.52)
        val pastaPotassium = ProductNutrientJoin(pasta.productId, potassium.nutrientId, 0.21)
        val pastaSodium = ProductNutrientJoin(pasta.productId, sodium.nutrientId, 0.83)
        val pastaVitaminA = ProductNutrientJoin(pasta.productId, vitaminA.nutrientId, 0.58)

        val spinachEnergy = ProductNutrientJoin(spinach.productId, energy.nutrientId, 0.23)
        val spinachProtein = ProductNutrientJoin(spinach.productId, protein.nutrientId, 0.3)
        val spinachCarbs = ProductNutrientJoin(spinach.productId, carbs.nutrientId, 0.27)
        val spinachSugars = ProductNutrientJoin(spinach.productId, sugars.nutrientId, 0.0)
        val spinachCalcium = ProductNutrientJoin(spinach.productId, calcium.nutrientId, 1.24)
        val spinachMagnesium = ProductNutrientJoin(spinach.productId, magnesium.nutrientId, 0.48)
        val spinachPhosphorus = ProductNutrientJoin(spinach.productId, phosphorus.nutrientId, 0.36)
        val spinachPotassium = ProductNutrientJoin(spinach.productId, potassium.nutrientId, 2.56)
        val spinachSodium = ProductNutrientJoin(spinach.productId, sodium.nutrientId, 0.55)
        val spinachVitaminC = ProductNutrientJoin(spinach.productId, vitaminC.nutrientId, 0.59)
        val spinachVitaminA = ProductNutrientJoin(spinach.productId, vitaminA.nutrientId, 11.58)

        val pearEnergy = ProductNutrientJoin(pear.productId, energy.nutrientId, 0.57)
        val pearProtein = ProductNutrientJoin(pear.productId, protein.nutrientId, 0.0)
        val pearCarbs = ProductNutrientJoin(pear.productId, carbs.nutrientId, 0.15)
        val pearSugars = ProductNutrientJoin(pear.productId, sugars.nutrientId, 0.1)
        val pearCalcium = ProductNutrientJoin(pear.productId, calcium.nutrientId, 0.09)
        val pearMagnesium = ProductNutrientJoin(pear.productId, magnesium.nutrientId, 0.07)
        val pearPhosphorus = ProductNutrientJoin(pear.productId, phosphorus.nutrientId, 0.12)
        val pearPotassium = ProductNutrientJoin(pear.productId, potassium.nutrientId, 1.16)
        val pearSodium = ProductNutrientJoin(pear.productId, sodium.nutrientId, 0.01)
        val pearVitaminC = ProductNutrientJoin(pear.productId, vitaminC.nutrientId, 0.04)
        val pearVitaminA = ProductNutrientJoin(pear.productId, vitaminA.nutrientId, 0.25)

        val guavaEnergy = ProductNutrientJoin(guava.productId, energy.nutrientId, 0.68)
        val guavaProtein = ProductNutrientJoin(guava.productId, protein.nutrientId, 0.0)
        val guavaCarbs = ProductNutrientJoin(guava.productId, carbs.nutrientId, 0.14)
        val guavaSugars = ProductNutrientJoin(guava.productId, sugars.nutrientId, 0.09)
        val guavaCalcium = ProductNutrientJoin(guava.productId, calcium.nutrientId, 0.18)
        val guavaMagnesium = ProductNutrientJoin(guava.productId, magnesium.nutrientId, 0.22)
        val guavaPhosphorus = ProductNutrientJoin(guava.productId, phosphorus.nutrientId, 0.4)
        val guavaPotassium = ProductNutrientJoin(guava.productId, potassium.nutrientId, 4.17)
        val guavaSodium = ProductNutrientJoin(guava.productId, sodium.nutrientId, 0.02)
        val guavaVitaminC = ProductNutrientJoin(guava.productId, vitaminC.nutrientId, 2.23)
        val guavaVitaminA = ProductNutrientJoin(guava.productId, vitaminA.nutrientId, 6.24)

        val potatoSmall = DatabasePortion(unit = "Small, 5cm", weight = 125.0, productId = potato.productId)
        val potatoMedium = DatabasePortion(unit = "Medium, 7cm", weight = 167.0, productId = potato.productId)
        val potatoBig = DatabasePortion(unit = "Large, 9cm", weight = 167.0, productId = potato.productId)

        val carrotSmall = DatabasePortion(unit = "Small, 14cm", weight = 50.0, productId = carrot.productId)
        val carrotMedium = DatabasePortion(unit = "Medium, 16cm", weight = 61.0, productId = carrot.productId)
        val carrotLarge = DatabasePortion(unit = "Large, 18.4cm", weight = 72.0, productId = carrot.productId)
        val carrotSlice = DatabasePortion(unit = "Slice", weight = 3.0, productId = carrot.productId)
        val carrotCup = DatabasePortion(unit = "Cup grated", weight = 110.0, productId = carrot.productId)

        val appleExtraSmall = DatabasePortion(unit = "Extra small, 6cm", weight = 101.0, productId = apple.productId)
        val appleSmall = DatabasePortion(unit = "Small, 7cm", weight = 150.0, productId = apple.productId)
        val appleMedium = DatabasePortion(unit = "Medium, 7.6cm", weight = 182.0, productId = apple.productId)
        val appleLarge = DatabasePortion(unit = "Large, 8.23cm", weight = 223.0, productId = apple.productId)
        val appleCupChopped = DatabasePortion(unit = "Cup, chopped", weight = 125.0, productId = apple.productId)
        val appleCupSlices = DatabasePortion(unit = "Cup, slices", weight = 109.0, productId = apple.productId)

        val milkCup = DatabasePortion(unit = "Cup", weight = 247.0, productId = milk.productId)

        val eggLarge = DatabasePortion(unit = "Large", weight = 50.0, productId = egg.productId)
        val eggSlice = DatabasePortion(unit = "Slice", weight = 5.0, productId = egg.productId)

        val bananaExtraSmall = DatabasePortion(unit = "Extra small, 14cm", weight = 81.0, productId = banana.productId)
        val bananaSmall = DatabasePortion(unit = "Small, 16.5cm", weight = 101.0, productId = banana.productId)
        val bananaMedium = DatabasePortion(unit = "Medium, 19cm", weight = 118.0, productId = banana.productId)
        val bananaLarge = DatabasePortion(unit = "Large, 22cm", weight = 136.0, productId = banana.productId)
        val bananaCupMashed = DatabasePortion(unit = "Cup, mashed", weight = 225.0, productId = banana.productId)
        val bananaCupSlices = DatabasePortion(unit = "Cup, slices", weight = 150.0, productId = banana.productId)

        val yogurtTbsp = DatabasePortion(unit = "Table spoon", weight = 15.0, productId = yogurt.productId)
        val yogurtTsp = DatabasePortion(unit = "Tea spoon", weight = 5.0, productId = yogurt.productId)
        val yogurtCup = DatabasePortion(unit = "Cup", weight = 245.0, productId = yogurt.productId)

        val chickenBreast = DatabasePortion(unit = "Brest", weight = 384.0, productId = chicken.productId)
        val chickenPiece = DatabasePortion(unit = "Piece", weight = 80.0, productId = chicken.productId)

        val orangeJuiceCup = DatabasePortion(unit = "Cup", weight = 240.0, productId = orangeJuice.productId)
        val grapefruitJuiceCup = DatabasePortion(unit = "Cup", weight = 240.0, productId = grapefruitJuice.productId)

        val zucchiniSmall = DatabasePortion(unit = "Small", weight = 118.0, productId = zucchini.productId)
        val zucchiniMedium = DatabasePortion(unit = "Medium", weight = 196.0, productId = zucchini.productId)
        val zucchiniLarge = DatabasePortion(unit = "Large", weight = 323.0, productId = zucchini.productId)
        val zucchiniSlice = DatabasePortion(unit = "Slice", weight = 10.0, productId = zucchini.productId)
        val zucchiniCup = DatabasePortion(unit = "Cup, slices", weight = 113.0, productId = zucchini.productId)

        val peaPod = DatabasePortion(unit = "Pod, 3-5 peas", weight = 4.0, productId = pea.productId)
        val peaTbsp = DatabasePortion(unit = "Table spoon", weight = 9.0, productId = pea.productId)
        val peaCup = DatabasePortion(unit = "Cup", weight = 63.0, productId = pea.productId)

        val tomatoSmall = DatabasePortion(unit = "Small", weight = 91.0, productId = tomato.productId)
        val tomatoMedium = DatabasePortion(unit = "Medium", weight = 123.0, productId = tomato.productId)
        val tomatoLarge = DatabasePortion(unit = "Large", weight = 182.0, productId = tomato.productId)
        val tomatoSlice = DatabasePortion(unit = "Slice", weight = 20.0, productId = tomato.productId)

        val cucumberSmall = DatabasePortion(unit = "Small", weight = 125.0, productId = cucumber.productId)
        val cucumberMedium = DatabasePortion(unit = "Medium", weight = 205.0, productId = cucumber.productId)
        val cucumberLarge = DatabasePortion(unit = "Large", weight = 300.0, productId = cucumber.productId)
        val cucumberSlice = DatabasePortion(unit = "Slice", weight = 18.0, productId = cucumber.productId)

        val lettuceInnerLeaf = DatabasePortion(unit = "Inner leaf", weight = 4.8, productId = lettuce.productId)
        val lettuceOuterLeaf = DatabasePortion(unit = "Outer leaf", weight = 24.0, productId = lettuce.productId)
        val lettuceCup = DatabasePortion(unit = "Cup, shredded", weight = 36.0, productId = lettuce.productId)

        val riceTbsp = DatabasePortion(unit = "Table spoon", weight = 12.0, productId = rice.productId)
        val riceTsp = DatabasePortion(unit = "Tea spoon", weight = 4.0, productId = rice.productId)
        val riceCup = DatabasePortion(unit = "Cup", weight = 158.0, productId = rice.productId)

        val pastaTbsp = DatabasePortion(unit = "Table spoon", weight = 6.0, productId = pasta.productId)
        val pastaTsp = DatabasePortion(unit = "Tea spoon", weight = 2.0, productId = pasta.productId)
        val pastaCup = DatabasePortion(unit = "Cup", weight = 100.0, productId = pasta.productId)

        val spinachBunch = DatabasePortion(unit = "Bunch", weight = 17.0, productId = spinach.productId)
        val spinachCup = DatabasePortion(unit = "Cup", weight = 44.0, productId = spinach.productId)

        val pearSmall = DatabasePortion(unit = "Small", weight = 148.0, productId = pear.productId)
        val pearMedium = DatabasePortion(unit = "Medium", weight = 178.0, productId = pear.productId)
        val pearLarge = DatabasePortion(unit = "Large", weight = 230.0, productId = pear.productId)
        val pearSlice = DatabasePortion(unit = "Slice", weight = 12.0, productId = pear.productId)
        val pearCup = DatabasePortion(unit = "Cup, slices", weight = 140.0, productId = pear.productId)

        val guavaSmall = DatabasePortion(unit = "Small", weight = 40.0, productId = guava.productId)
        val guavaMedium = DatabasePortion(unit = "Medium", weight = 55.0, productId = guava.productId)
        val guavaLarge = DatabasePortion(unit = "Large", weight = 68.0, productId = guava.productId)
        val guavaSlice = DatabasePortion(unit = "Slice", weight = 8.0, productId = guava.productId)

        GlobalScope.launch {
            foodDatabase.productDao.insertProducts(
                listOf(
                    apple,
                    carrot,
                    potato,
                    milk,
                    egg,
                    banana,
                    yogurt,
                    chicken,
                    orangeJuice,
                    grapefruitJuice,
                    zucchini,
                    pea,
                    tomato,
                    cucumber,
                    lettuce,
                    rice,
                    pasta,
                    spinach,
                    pear,
                    guava
                )
            )
            foodDatabase.nutrientDao.insertNutrients(
                listOf(
                    energy,
                    carbs,
                    protein,
                    sugars,
                    vitaminA,
                    potassium,
                    vitaminC,
                    sodium,
                    vitaminE,
                    vitaminK,
                    calcium,
                    magnesium,
                    phosphorus,
                    zinc,
                    vitaminD,
                    vitaminB
                )
            )
            foodDatabase.productNutrientJoinDao.insert(
                listOf(
                    potatoEnergy, potatoCarbs, potatoProtein,
                    potatoSugars, potatoVitaminC, potatoPotassium,

                    appleEnergy, appleCarbs, appleSugars, applePotassium, appleVitaminA,

                    cucumberEnergy, cucumberCarbs, cucumberSugars, cucumberCalcium,
                    cucumberMagnesium, cucumberPhosphorus, cucumberPotassium,
                    cucumberSodium, cucumberVitaminC, cucumberVitaminA, cucumberVitaminK,

                    lettuceEnergy, lettuceProtein, lettuceCarbs, lettuceCalcium,
                    lettuceMagnesium, lettucePhosphorus, lettucePotassium, lettuceSodium,
                    lettuceVitaminC, lettuceVitaminA, lettuceVitaminK,

                    carrotCarbs, carrotVitaminA, carrotProtein, carrotSugars, carrotPotassium, carrotVitaminC,
                    carrotVitaminE, carrotVitaminK,

                    milkEnergy, milkProtein, milkCarbs, milkSugars, milkCalcium, milkMagnesium, milkPhosphorus,
                    milkPotassium, milkSodium, milkVitaminA, milkVitaminC, milkVitaminD,

                    eggEnergy, eggProtein, eggCarbs, eggSugars, eggPhosphorus, eggPotassium, eggSodium, eggZinc,
                    eggVitaminB, eggVitaminA, eggVitaminE, eggVitaminD,

                    bananaEnergy, bananaProtein, bananaCarbs, bananaSugars,
                    bananaPotassium, bananaVitaminC, bananaVitaminA,

                    yogurtEnergy, yogurtProtein, yogurtCarbs, yogurtSugars,
                    yogurtCalcium, yogurtPhosphorus, yogurtPotassium, yogurtSodium,

                    chickenEnergy, chickenProtein, chickenPhosphorus, chickenPotassium,
                    chickenSodium, chickenZinc, chickenVitaminA,

                    orangeJuiceEnergy, orangeJuiceProtein, orangeJuiceCarbs, orangeJuiceSugars,
                    orangeJuicePotassium, orangeJuiceVitaminC,

                    grapefruitJuiceEnergy, grapefruitJuiceCarbs, grapefruitJuiceSugars,
                    grapefruitJuiceCalcium, grapefruitJuicePotassium, grapefruitJuiceVitaminC,

                    zucchiniEnergy, zucchiniCalcium, zucchiniMagnesium, zucchiniPhosphorus,
                    zucchiniPotassium, zucchiniSodium, zucchiniVitaminC, zucchiniVitaminA, zucchiniVitaminK,

                    peasEnergy, peasProtein, peasCarbs, peasSugars, peasCalcium, peasPhosphorus, peasSodium,
                    peasVitaminC, peasVitaminA, peasVitaminK,

                    tomatoEnergy, tomatoCarbs, tomatoSugars, tomatoCalcium, tomatoMagnesium, tomatoPhosphorus,
                    tomatoPotassium, tomatoSodium, tomatoVitaminC, tomatoVitaminA, tomatoVitaminK,

                    riceEnergy, riceProtein, riceCarbs, riceCalcium, riceMagnesium,
                    ricePhosphorus, ricePotassium, riceSodium, pastaEnergy,

                    pastaProtein, pastaCarbs, pastaCalcium, pastaMagnesium,
                    pastaPhosphorus, pastaPotassium, pastaSodium, pastaVitaminA,

                    spinachEnergy, spinachProtein, spinachCarbs, spinachCalcium, spinachMagnesium,
                    spinachPhosphorus, spinachPotassium, spinachSodium, spinachVitaminC, spinachVitaminA,

                    pearEnergy, pearCarbs, pearSugars, pearCalcium, pearMagnesium,
                    pearPhosphorus, pearPotassium, pearSodium, pearVitaminC, pearVitaminA,

                    guavaEnergy, guavaCarbs, guavaSugars, guavaCalcium, guavaMagnesium,
                    guavaPhosphorus, guavaPotassium, guavaSodium, guavaVitaminC, guavaVitaminA,

                    potatoProtein, carrotEnergy, appleProtein, chickenCarbs, chickenSugars,
                    grapefruitJuiceProtein, zucchiniProtein, zucchiniCarbs, zucchiniSugars,
                    tomatoProtein, cucumberProtein, lettuceSugars, riceSugars, pastaSugars,
                    spinachSugars, pearProtein, guavaProtein
                )
            )
        }

        GlobalScope.launch {
            delay(1000)
            foodDatabase.portionDao.insertPortions(
                listOf(
                    potatoSmall, potatoMedium, potatoBig,

                    carrotSmall, carrotMedium, carrotLarge, carrotSlice, carrotCup,

                    appleExtraSmall, appleSmall, appleMedium, appleLarge, appleCupChopped, appleCupSlices,

                    milkCup,

                    eggLarge, eggSlice,

                    bananaExtraSmall, bananaSmall, bananaMedium, bananaLarge, bananaCupMashed, bananaCupSlices,

                    yogurtTbsp, yogurtTsp, yogurtCup,

                    chickenBreast, chickenPiece,

                    orangeJuiceCup,

                    grapefruitJuiceCup,

                    zucchiniSmall, zucchiniMedium, zucchiniLarge, zucchiniSlice, zucchiniCup,

                    peaPod, peaTbsp, peaCup,

                    tomatoSmall, tomatoMedium, tomatoLarge, tomatoSlice,

                    cucumberSmall, cucumberMedium, cucumberLarge, cucumberSlice,

                    lettuceInnerLeaf, lettuceOuterLeaf, lettuceCup,

                    riceTbsp, riceTsp, riceCup,

                    pastaTbsp, pastaTsp, pastaCup,

                    spinachBunch, spinachCup,

                    pearSmall, pearMedium, pearLarge, pearSlice, pearCup,

                    guavaSmall, guavaMedium, guavaLarge, guavaSlice
                )
            )
        }

    }
}
/*******************************************************************************
 * The MIT License (MIT)
 * 
 * Copyright (C) 2014-2018 Sam Bassett (aka Lothrazar)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.lothrazar.absentbydesign.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import com.lothrazar.absentbydesign.ModAbsentBD;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * 
 * parts of this entire file as of 1.12 (not just this subclass) are from OSS https://github.com/TechReborn/RebornCore/blob/1.12/src/main/java/reborncore/common/util/RebornCraftingHelper.java copy
 * pasted their notice here * Copyright (c) 2017 modmuss50 and Gigabit101
 *
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to
 * do so, subject to the following conditions:
 *
 *
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * @author Sam
 *
 */
public class RecipeRegistry {

  private static Map<String, Boolean> usedRecipeNames = new HashMap<String, Boolean>();
  public static List<IRecipe> recipes = new ArrayList<IRecipe>();

  public static class Util1pt12 {

    public static ResourceLocation buildName(ItemStack output) {
      ResourceLocation firstTry = new ResourceLocation(ModAbsentBD.MODID, output.getUnlocalizedName());
      int limit = 999;
      int index = 0;
      while (usedRecipeNames.containsKey(firstTry.toString()) || index > limit) {
        index++;
        firstTry = new ResourceLocation(ModAbsentBD.MODID, firstTry.getResourcePath() + "_" + index);
      }
      usedRecipeNames.put(firstTry.toString(), true);
      return firstTry;
    }

    private static NonNullList<Ingredient> convertToNonNullList(Object[] input) {
      NonNullList<Ingredient> list = NonNullList.create();
      for (Object any : input) {
        if (any instanceof Ingredient) {
          list.add((Ingredient) any);
        }
        else {
          Ingredient ing = CraftingHelper.getIngredient(any);
          if (ing == null) {
            ing = Ingredient.EMPTY;// EMPTY/.. same as new Ingredient(new ItemStack[0])
          }
          list.add(ing);
        }
      }
      return list;
    }
  }

  private static void add(IRecipe r, ResourceLocation location) {
    r.setRegistryName(location);
    recipes.add(r);
  }

  /**
   * thin wrapper for addShapedRecipe
   * 
   * @param output
   * @param params
   * @return
   */
  private static IRecipe _addShapedRecipe(ItemStack output, Object... params) {
    CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(params);
    ShapedRecipes recipe = new ShapedRecipes(output.getItem().getRegistryName().toString(), primer.width, primer.height, primer.input, output);
    add(recipe, Util1pt12.buildName(output));
    return recipe;
  }

  public static IRecipe addShapedRecipe(@Nonnull ItemStack output, Object... recipeComponents) {
    for (Object object : recipeComponents) {
      if (object instanceof String) {
        return addShapedOreRecipe(output, recipeComponents);
      }
    }
    return _addShapedRecipe(output, recipeComponents);
  }

  private static IRecipe addShapedOreRecipe(ItemStack output, Object... recipeComponents) {
    if (output.isEmpty()) {
      throw new IllegalArgumentException("cannot add recipe for air");
    }
    ResourceLocation location = Util1pt12.buildName(output);
    IRecipe recipe = new ShapedOreRecipe(location, output, recipeComponents);
    add(recipe, Util1pt12.buildName(output));
    return recipe;
  }
}

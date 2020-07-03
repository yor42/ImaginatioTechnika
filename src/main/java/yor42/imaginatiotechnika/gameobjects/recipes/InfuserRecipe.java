package yor42.imaginatiotechnika.gameobjects.recipes;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yor42.imaginatiotechnika.init.ItemInit;

import javax.annotation.Nullable;
import java.util.Map;

public class InfuserRecipe {

    //Thank you Alchemi1963! saved my day.//

    private static final InfuserRecipe INSTANCE = new InfuserRecipe();
    private final Table<ItemStack[], ItemStack, ItemStack> InfusingList = HashBasedTable.create();

    private final Map<ItemStack, ItemStack[]> parsedrecipe = Maps.<ItemStack, ItemStack[]>newHashMap();

    public static InfuserRecipe getInstance(){
        return INSTANCE;
    }

    private InfuserRecipe(){
        addInfusingRecipe(new ItemStack(ItemInit.pureoriginium,1), new ItemStack(ItemInit.rma7024_ingot,1),new ItemStack(ItemInit.manganese_ingot,1), new ItemStack(ItemInit.d32_ingot,1));
    }

    public void addInfusingRecipe(ItemStack input0,ItemStack input1,ItemStack input2,ItemStack result){
        if(getInfusingResult(input0, input1, input2) != ItemStack.EMPTY) return;
        this.InfusingList.put(new ItemStack[]{input0, input1}, input2, result);
        this.parsedrecipe.put(result, new ItemStack[] {input0, input1, input2});
    }

    private boolean compareEntries(ItemStack[] attempt, ItemStack[] itemStacks) {

        return (this.compareItemStacks(attempt[0], itemStacks[0]) && this.compareItemStacks(attempt[1], itemStacks[1])) || (this.compareItemStacks(attempt[1], itemStacks[0]) && this.compareItemStacks(attempt[0], itemStacks[1]));
    }

    private boolean compareItemStacks(ItemStack input0, ItemStack key) {

        return key.getItem().equals(input0.getItem()) && (key.getMetadata() == 32767 || input0.getMetadata() == key.getMetadata());
    }

    public ItemStack getInfusingResult(ItemStack input0, ItemStack input1, ItemStack input2) {
        ItemStack[] try1 = new ItemStack[] {input0, input1};
        ItemStack[] try2 = new ItemStack[] {input0, input2};
        ItemStack[] try3 = new ItemStack[] {input1, input2};
        ItemStack[] try4 = new ItemStack[] {input1, input0};
        ItemStack[] try5 = new ItemStack[] {input2, input0};
        ItemStack[] try6 = new ItemStack[] {input2, input1};

        ItemStack[][] tries = new ItemStack[][] {try1, try2, try3, try4, try5, try6};

        for (ItemStack[] attempt : tries) {
            for (Map.Entry<ItemStack, Map<ItemStack[], ItemStack>> entry : this.InfusingList.columnMap().entrySet()) {

                if (this.compareItemStacks(input0, (ItemStack)entry.getKey()) || this.compareItemStacks(input1, (ItemStack)entry.getKey()) || this.compareItemStacks(input2, (ItemStack)entry.getKey())) {

                    for (Map.Entry<ItemStack[], ItemStack> ent : entry.getValue().entrySet()) {

                        if (this.compareEntries(attempt, (ItemStack[])ent.getKey())) {
                            return (ItemStack)ent.getValue();
                        }
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public Table<ItemStack[], ItemStack, ItemStack> getInfusingList() {
        return this.InfusingList;
    }

    public int getItemQuantity(ItemStack result, ItemStack input) {

        if (this.parsedrecipe.containsKey(result)) {

            for (ItemStack stack : this.parsedrecipe.get(result)) {
                if (this.compareItemStacks(stack, input)) {
                    return stack.getCount();
                }
            }

        }

        return 0;
    }

    public Map<ItemStack, ItemStack[]> getParsedrecipe() {
        return parsedrecipe;
    }

}

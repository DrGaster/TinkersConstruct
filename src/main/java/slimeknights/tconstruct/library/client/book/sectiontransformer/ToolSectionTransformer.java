package slimeknights.tconstruct.library.client.book.sectiontransformer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.content.ContentListing;
import slimeknights.mantle.client.book.transformer.ContentListingSectionTransformer;
import slimeknights.tconstruct.library.client.book.content.ContentTool;
import slimeknights.tconstruct.library.tools.item.IModifiableDisplay;

/** Section transformer to generate an index with tool names */
public class ToolSectionTransformer extends ContentListingSectionTransformer {
  public static final ToolSectionTransformer INSTANCE = new ToolSectionTransformer("tools");

  public ToolSectionTransformer(String name, boolean largeTitle, boolean centerTitle) {
    super(name, largeTitle, centerTitle);
  }

  public ToolSectionTransformer(String name) {
    super(name, null, null);
  }

  @Override
  protected boolean processPage(BookData book, ContentListing listing, PageData page) {
    // only add tool pages if the tool exists
    if (page.content instanceof ContentTool) {
      ResourceLocation toolId = new ResourceLocation(((ContentTool) page.content).toolName);
      if (ForgeRegistries.ITEMS.containsKey(toolId)) {
        Item toolItem = ForgeRegistries.ITEMS.getValue(toolId);
        if (toolItem instanceof IModifiableDisplay) {
          listing.addEntry(((IModifiableDisplay)toolItem).getLocalizedName().getString(), page);
        }
      }
    } else {
      super.processPage(book, listing, page);
    }
    return true;
  }
}
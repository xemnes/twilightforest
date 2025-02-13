package twilightforest.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import com.mojang.blaze3d.platform.Lighting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import twilightforest.TwilightForestMod;
import twilightforest.block.TFBlocks;
import twilightforest.inventory.UncraftingContainer;
import twilightforest.network.UncraftingGuiPacket;
import twilightforest.network.TFPacketHandler;

import net.minecraft.client.gui.components.Button.OnPress;

public class UncraftingGui extends AbstractContainerScreen<UncraftingContainer> {

	private static final ResourceLocation textureLoc = TwilightForestMod.getGuiTexture("guigoblintinkering.png");

	public UncraftingGui(UncraftingContainer container, Inventory player, Component name) {
		super(container, player, name);
	}

	@Override
	protected void init() {
		super.init();

		this.addWidget(new CycleButton(leftPos + 40, topPos + 22, true, button -> {
			TFPacketHandler.CHANNEL.sendToServer(new UncraftingGuiPacket(0));
			menu.unrecipeInCycle++;
			menu.slotsChanged(menu.tinkerInput);
		}));
		this.addWidget(new CycleButton(leftPos + 40, topPos + 55, false, button -> {
			TFPacketHandler.CHANNEL.sendToServer(new UncraftingGuiPacket(1));
			menu.unrecipeInCycle--;
			menu.slotsChanged(menu.tinkerInput);
		}));

		//this.buttonList.add(new ModeButton(uiLeft + 7, guiTop + 57));

		this.addWidget(new CycleButtonMini(leftPos + 27, topPos + 56, true, button -> {
			TFPacketHandler.CHANNEL.sendToServer(new UncraftingGuiPacket(2));
			menu.ingredientsInCycle++;
			menu.slotsChanged(menu.tinkerInput);
		}));
		this.addWidget(new CycleButtonMini(leftPos + 27, topPos + 63, false, button -> {
			TFPacketHandler.CHANNEL.sendToServer(new UncraftingGuiPacket(3));
			menu.ingredientsInCycle--;
			menu.slotsChanged(menu.tinkerInput);
		}));

		//this.buttonList.add(new RefreshButton(uiLeft + 26, guiTop + 57));

		this.addWidget(new CycleButton(leftPos + 121, topPos + 22, true, button -> {
			TFPacketHandler.CHANNEL.sendToServer(new UncraftingGuiPacket(4));
			menu.unrecipeInCycle++;
			menu.slotsChanged(menu.assemblyMatrix);
		}));
		this.addWidget(new CycleButton(leftPos + 121, topPos + 55, false, button -> {
			TFPacketHandler.CHANNEL.sendToServer(new UncraftingGuiPacket(5));
			menu.unrecipeInCycle--;
			menu.slotsChanged(menu.assemblyMatrix);
		}));
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		renderTooltip(ms, mouseX, mouseY); //renderHoveredToolTip
	}

	@Override
	protected void renderLabels(PoseStack ms, int mouseX, int mouseY) {
		this.font.draw(ms, I18n.get(TFBlocks.uncrafting_table.get().getDescriptionId()), 8, 6, 4210752);
		this.font.draw(ms, I18n.get("container.inventory"), 8, this.imageHeight - 96 + 2, 4210752);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		this.minecraft.getTextureManager().getTexture(textureLoc);
		int frameX = (this.width - this.imageWidth) / 2;
		int frameY = (this.height - this.imageHeight) / 2;
		this.blit(ms, frameX, frameY, 0, 0, this.imageWidth, this.imageHeight);

		UncraftingContainer tfContainer = this.menu;

		// show uncrafting ingredients as background
		ms.pushPose();
		ms.translate(leftPos, topPos, 0);

		for (int i = 0; i < 9; i++) {
			Slot uncrafting = tfContainer.getSlot(2 + i);
			Slot assembly = tfContainer.getSlot(11 + i);

			if (uncrafting.hasItem()) {
				drawSlotAsBackground(ms, uncrafting, assembly);
			}
		}
		ms.popPose();

		// show the costs if there are any
		Font fontRendererObj = this.minecraft.font;
		Lighting.setupForFlatItems();

		int costVal = tfContainer.getUncraftingCost();
		if (costVal > 0) {
			int color;
			String cost = "" + costVal;
			if (this.minecraft.player.experienceLevel < costVal && !this.minecraft.player.abilities.instabuild) {
				color = 0xA00000;
			} else {
				color = 0x80FF20;
			}
			fontRendererObj.drawShadow(ms, cost, frameX + 48 - fontRendererObj.width(cost), frameY + 38, color);
		}

		costVal = tfContainer.getRecraftingCost();
		if (costVal > 0) {
			int color;
			String cost = "" + costVal;
			if (this.minecraft.player.experienceLevel < costVal && !this.minecraft.player.abilities.instabuild) {
				color = 0xA00000;
			} else {
				color = 0x80FF20;
			}
			fontRendererObj.drawShadow(ms, cost, frameX + 130 - fontRendererObj.width(cost), frameY + 38, color);
		}
	}

	private void drawSlotAsBackground(PoseStack ms, Slot backgroundSlot, Slot appearSlot) {

		int screenX = appearSlot.x + leftPos;
		int screenY = appearSlot.y + topPos;
		ItemStack itemStackToRender = backgroundSlot.getItem();
		itemRenderer.blitOffset = 50.0F;

        itemRenderer.renderGuiItem(itemStackToRender, screenX, screenY);
        itemRenderer.renderGuiItemDecorations(this.font, itemStackToRender, screenX, screenY, "");

		boolean itemBroken = UncraftingContainer.isMarked(itemStackToRender);

		// draw 50% gray rectangle over the item
		RenderSystem.disableDepthTest();
		GuiComponent.fill(ms, appearSlot.x, appearSlot.y, appearSlot.x + 16, appearSlot.y + 16, itemBroken ? 0x80FF8b8b : 0x9f8b8b8b);
		RenderSystem.enableDepthTest();

        itemRenderer.blitOffset = 0.0F;
	}

	private static class CycleButton extends Button {
		private final boolean up;

		CycleButton(int x, int y, boolean up, OnPress onClick) {
			super(x, y, 14, 9, TextComponent.EMPTY, onClick);
			this.up = up;
		}

		@Override
		public void renderButton(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
			if (this.visible) {
				Minecraft.getInstance().getTextureManager().getTexture(UncraftingGui.textureLoc);
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

				int textureX = 176;
				int textureY = 0;

				if (this.isHovered) textureX += this.width;

				// what's up
				if (!this.up) textureY += this.height;

				this.blit(ms, this.x, this.y, textureX, textureY, this.width, this.height);
			}
		}
	}

//	private static class ModeButton extends GuiButton {
//		ModeButton(int buttonId, int x, int y) {
//			super(buttonId, x, y, 18, 12, "");
//		}

//		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
//			if (this.visible) {
//				mc.getTextureManager().bindTexture(GuiTFGoblinCrafting.textureLoc);
//				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//				this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

//				int textureX = 176;
//				int textureY = 18;

//				if (this.hovered) textureX += this.width;

//				this.drawTexturedModalRect(this.x, this.y, textureX, textureY, this.width, this.height);
//			}
//		}
//	}

	private static class CycleButtonMini extends Button {
		private final boolean up;

		CycleButtonMini(int x, int y, boolean up, OnPress onClick) {
			super(x, y, 8, 6, TextComponent.EMPTY, onClick);
			this.up = up;
		}

		@Override
		public void renderButton(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
			if (this.visible) {
				Minecraft.getInstance().getTextureManager().getTexture(UncraftingGui.textureLoc);
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

				int textureX = 176;
				int textureY = 41;

				if (this.isHovered) textureX += this.width;

				// what's up
				if (!this.up) textureY += this.height;

				this.blit(ms, this.x, this.y, textureX, textureY, this.width, this.height);
			}
		}
	}

//	static class RefreshButton extends GuiButton {
//		RefreshButton(int buttonId, int x, int y) {
//			super(buttonId, x, y, 8, 6, "");
//		}

//		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
//			if (this.visible) {
//				mc.getTextureManager().bindTexture(GuiTFGoblinCrafting.textureLoc);
//				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//				this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

//				int textureX = 176;
//				int textureY = 30;

//				if (this.hovered) textureX += this.width;

//				this.drawTexturedModalRect(this.x, this.y, textureX, textureY, this.width, this.height);
//			}
//		}
//	}
}

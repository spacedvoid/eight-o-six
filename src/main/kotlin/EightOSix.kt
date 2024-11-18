package io.github.spacedvoid.eightosix

import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.transformers.pages.tags.CustomTagContentProvider
import org.jetbrains.dokka.base.translators.documentables.PageContentBuilder.DocumentableContentBuilder
import org.jetbrains.dokka.model.doc.CustomTagWrapper
import org.jetbrains.dokka.plugability.DokkaPlugin
import org.jetbrains.dokka.plugability.DokkaPluginApiPreview
import org.jetbrains.dokka.plugability.PluginApiPreviewAcknowledgement

class EightOSix: DokkaPlugin() {
	@OptIn(DokkaPluginApiPreview::class)
	override fun pluginApiPreviewAcknowledgement(): PluginApiPreviewAcknowledgement = PluginApiPreviewAcknowledgement

	@Suppress("unused")
	val customTags by extending {
		plugin<DokkaBase>().customTagContentProvider with CustomTagTranslator()
	}
}

class CustomTagTranslator: CustomTagContentProvider {
	companion object {
		private val tagNames = mapOf(
			"apiNote" to "API Note",
			"implSpec" to "Implementation Specification",
			"implNote" to "Implementation Note"
		)
	}

	override fun isApplicable(customTag: CustomTagWrapper): Boolean = customTag.name in tagNames

	override fun DocumentableContentBuilder.contentForDescription(sourceSet: DokkaConfiguration.DokkaSourceSet, customTag: CustomTagWrapper) {
		header(
			level = 3,
			text = tagNames.getValue(customTag.name)
		)
		customTag.children.forEach(::comment)
	}
}

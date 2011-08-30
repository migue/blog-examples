<xsl:stylesheet xmlns:t="http://nwalsh.com/docbook/xsl/template/1.0"
	xmlns:param="http://nwalsh.com/docbook/xsl/template/1.0/param"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:import href="urn:docbkx:stylesheet" />
	<!--<xsl:import href="cover.xsl" />-->

	<!-- Configuracion global -->
	<xsl:param name="hyphenate">false</xsl:param>
	<xsl:param name="headers.on.blank.pages">0</xsl:param>
	<xsl:param name="footers.on.blank.pages">0</xsl:param>	

	<xsl:param name="l10n.gentext.default.language">es</xsl:param>

	<!-- Configuracion de las paginas -->
	<xsl:param name="page.margin.top">20mm</xsl:param>
	<xsl:param name="page.margin.right">20mm</xsl:param>
	<xsl:param name="page.margin.bottom">20mm</xsl:param>
	<xsl:param name="page.margin.left">25mm</xsl:param>
	<xsl:param name="region.before.extent">10mm</xsl:param>
	<xsl:param name="region.after.extent">10mm</xsl:param>
	<xsl:param name="body.start.indent">15mm</xsl:param>
	<xsl:param name="body.margin.top">15mm</xsl:param>
	<xsl:param name="body.margin.bottom">10mm</xsl:param>

	<!-- Tipografia -->
	<xsl:param name="title.font.family">Georgia</xsl:param>
	<xsl:param name="body.font.family">Calibri</xsl:param>
	<xsl:param name="dingbat.font.family">Calibri</xsl:param>
	<xsl:param name="body.font.master">11</xsl:param>
	<xsl:param name="alignment">justify</xsl:param>
	<xsl:param name="line-height">normal</xsl:param>


	<!-- Cabecera -->
	<xsl:param name="header.rule" select="0"></xsl:param>

	<xsl:attribute-set name="header.content.properties">
		<xsl:attribute name="font-family">Georgia</xsl:attribute>
		<xsl:attribute name="font-size">8pt</xsl:attribute>
	</xsl:attribute-set>

	<xsl:template name="header.content">
		<xsl:param name="pageclass" select="''" />
		<xsl:param name="sequence" select="''" />
		<xsl:param name="position" select="''" />
		<xsl:param name="gentext-key" select="''" />
		<fo:block>
			<xsl:choose>
				<xsl:when
					test="($pageclass='body' or $pageclass='front') and $position = 'left'">
					<fo:block>
						<xsl:value-of
							select="concat(/book/title,' ',/book/bookinfo/releaseinfo)" />
					</fo:block>
					<fo:block>
						<xsl:value-of
							select="ancestor-or-self::book/subtitle" />
					</fo:block>
				</xsl:when>
				<xsl:when
					test="$pageclass='body' and $position = 'right'">
					<fo:block>
						<xsl:apply-templates select="."
							mode="titleabbrev.markup" />
					</fo:block>
				</xsl:when>
			</xsl:choose>
		</fo:block>
	</xsl:template>
	
	<!-- Pie de pagina -->
	<xsl:param name="footer.rule" select="0"></xsl:param>
	<xsl:attribute-set name="footer.content.properties">
		<xsl:attribute name="font-family">Georgia</xsl:attribute>
		<xsl:attribute name="font-size">9pt</xsl:attribute>
	</xsl:attribute-set>
	<xsl:template name="footer.content">
		<xsl:param name="pageclass" select="''" />
		<xsl:param name="sequence" select="''" />
		<xsl:param name="position" select="''" />
		<xsl:param name="gentext-key" select="''" />
		<fo:block>
			<xsl:choose>
				<xsl:when test="$position = 'right'">
					<fo:page-number />
				</xsl:when>
			</xsl:choose>

		</fo:block>
	</xsl:template>

	<!-- Propiedades de los títulos de los capítulos -->
	<xsl:attribute-set name="component.title.properties">
		<xsl:attribute name="font-size">26</xsl:attribute>
		<xsl:attribute name="font-family">Georgia</xsl:attribute>
		<xsl:attribute name="space-before.minimum">60pt</xsl:attribute>
		<xsl:attribute name="space-before.optimum">60pt</xsl:attribute>
		<xsl:attribute name="space-before.maximum">60pt</xsl:attribute>
		<xsl:attribute name="space-after.minimum">20pt</xsl:attribute>
		<xsl:attribute name="space-after.optimum">20pt</xsl:attribute>
		<xsl:attribute name="space-after.maximum">20pt</xsl:attribute>
		<xsl:attribute name="border-bottom">
			0.5pt solid black
		</xsl:attribute>
		<xsl:attribute name="padding-bottom">10pt</xsl:attribute>
		<xsl:attribute name="padding-top">70pt</xsl:attribute>
		<xsl:attribute name="text-align">end</xsl:attribute>
	</xsl:attribute-set>

	<!-- Quitar Capítulo1, Capítulo2 ... -->
	<xsl:param name="chapter.autolabel" select="1"></xsl:param>

	<!-- Secciones -->
	<xsl:attribute-set name="section.title.properties">
		<xsl:attribute name="space-after.minimum">5mm</xsl:attribute>
		<xsl:attribute name="space-after.optimum">5mm</xsl:attribute>
		<xsl:attribute name="space-after.maximum">5mm</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="section.title.level1.properties">
		<xsl:attribute name="font-size">16</xsl:attribute>
		<xsl:attribute name="border-bottom">
			0.5pt solid black
		</xsl:attribute>
		<xsl:attribute name="space-before.minimum">15mm</xsl:attribute>
		<xsl:attribute name="space-before.optimum">15mm</xsl:attribute>
		<xsl:attribute name="space-before.maximum">15mm</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="section.title.level2.properties">
		<xsl:attribute name="font-size">14</xsl:attribute>
		<xsl:attribute name="border-bottom">
			0.5pt solid black
		</xsl:attribute>
		<xsl:attribute name="margin-left">15mm</xsl:attribute>
		<xsl:attribute name="space-before.minimum">10mm</xsl:attribute>
		<xsl:attribute name="space-before.optimum">10mm</xsl:attribute>
		<xsl:attribute name="space-before.maximum">10mm</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="section.title.level3.properties">
		<xsl:attribute name="font-size">13</xsl:attribute>
		<xsl:attribute name="border-bottom">
			0.5pt solid black
		</xsl:attribute>
		<xsl:attribute name="margin-left">15mm</xsl:attribute>
		<xsl:attribute name="space-before.minimum">10mm</xsl:attribute>
		<xsl:attribute name="space-before.optimum">10mm</xsl:attribute>
		<xsl:attribute name="space-before.maximum">10mm</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="section.title.level4.properties">
		<xsl:attribute name="font-size">12</xsl:attribute>
		<xsl:attribute name="border-bottom">
			0.5pt solid black
		</xsl:attribute>		
		<xsl:attribute name="margin-left">18mm</xsl:attribute>
		<xsl:attribute name="space-before.minimum">10mm</xsl:attribute>
		<xsl:attribute name="space-before.optimum">10mm</xsl:attribute>
		<xsl:attribute name="space-before.maximum">10mm</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="section.title.level5.properties">
		<xsl:attribute name="font-size">11</xsl:attribute>
		<xsl:attribute name="border-bottom">
			0.5pt solid black
		</xsl:attribute>
		<xsl:attribute name="margin-left">20mm</xsl:attribute>
		<xsl:attribute name="space-before.minimum">10mm</xsl:attribute>
		<xsl:attribute name="space-before.optimum">10mm</xsl:attribute>
		<xsl:attribute name="space-before.maximum">10mm</xsl:attribute>
	</xsl:attribute-set>	

	<!-- Propiedades de los objetos (figuras) -->
	<xsl:attribute-set name="formal.object.properties">
		<xsl:attribute name="space-before.minimum">10mm</xsl:attribute>
		<xsl:attribute name="space-before.optimum">10mm</xsl:attribute>
		<xsl:attribute name="space-before.maximum">10mm</xsl:attribute>
		<xsl:attribute name="space-after.minimum">10mm</xsl:attribute>
		<xsl:attribute name="space-after.optimum">10mm</xsl:attribute>
		<xsl:attribute name="space-after.maximum">10mm</xsl:attribute>
	</xsl:attribute-set>

	<!-- Títulos de los objetos -->
	<xsl:attribute-set name="formal.title.properties">
		<xsl:attribute name="font-size">10pt</xsl:attribute>
		<xsl:attribute name="hyphenate">false</xsl:attribute>
		<xsl:attribute name="font-style">
			<xsl:choose>
				<xsl:when test="self::figure">italic</xsl:when>
				<xsl:otherwise>inherit</xsl:otherwise>
			</xsl:choose>
		</xsl:attribute>
		<xsl:attribute name="font-weight">
			<xsl:choose>
				<xsl:when test="self::figure">normal</xsl:when>
				<xsl:otherwise>inherit</xsl:otherwise>
			</xsl:choose>
		</xsl:attribute>
	</xsl:attribute-set>

	<!-- Propiedades de las listas -->
	<xsl:attribute-set name="list.item.spacing">
		<xsl:attribute name="space-before.optimum">2.5mm</xsl:attribute>
		<xsl:attribute name="space-before.minimum">2.5mm</xsl:attribute>
		<xsl:attribute name="space-before.maximum">2.5mm</xsl:attribute>
	</xsl:attribute-set>
	<xsl:attribute-set name="list.block.spacing">
		<xsl:attribute name="space-before.optimum">5mm</xsl:attribute>
		<xsl:attribute name="space-before.minimum">5mm</xsl:attribute>
		<xsl:attribute name="space-before.maximum">5mm</xsl:attribute>
		<xsl:attribute name="space-after.optimum">5mm</xsl:attribute>
		<xsl:attribute name="space-after.minimum">5mm</xsl:attribute>
		<xsl:attribute name="space-after.maximum">5mm</xsl:attribute>
	</xsl:attribute-set>
	<!-- Ancho entre el punto y el texto -->
	<xsl:param name="itemizedlist.label.width">1.0em</xsl:param>



	<!-- Placeholder templates -->
	<xsl:template name="front.cover"></xsl:template>
	<xsl:template name="back.cover"></xsl:template>

	<!-- Niveles del índice a mostrar -->
	<xsl:param name="toc.section.depth">8</xsl:param>

	<!-- Para generar número de etiquetas  -->
	<xsl:param name="section.autolabel.max.depth">8</xsl:param>
	<xsl:param name="section.autolabel" select="1"></xsl:param>
	<xsl:param name="section.label.includes.component.label"
		select="1">
	</xsl:param>


	<!-- Quita el título de las notas (Nota)-->
	<xsl:param name="admon.textlabel" select="0"></xsl:param>

	<!-- Propiedades de las notas -->
	<xsl:attribute-set name="admonition.properties">
		<xsl:attribute name="background-color">#E0E0E0</xsl:attribute>
		<xsl:attribute name="padding-top">15pt</xsl:attribute>
		<xsl:attribute name="padding-bottom">15pt</xsl:attribute>
		<xsl:attribute name="padding-left">15pt</xsl:attribute>
		<xsl:attribute name="padding-right">15pt</xsl:attribute>
		<xsl:attribute name="margin-top">0pt</xsl:attribute>
		<xsl:attribute name="margin-left">0pt</xsl:attribute>
		<xsl:attribute name="margin-right">0pt</xsl:attribute>
		<xsl:attribute name="margin-bottom">0pt</xsl:attribute>
	</xsl:attribute-set>

	<!-- Propiedades de los margenes de las notas -->
	<xsl:attribute-set name="nongraphical.admonition.properties">
		<xsl:attribute name="space-before.minimum">0.8em</xsl:attribute>
		<xsl:attribute name="space-before.optimum">1em</xsl:attribute>
		<xsl:attribute name="space-before.maximum">1.2em</xsl:attribute>
		<xsl:attribute name="space-after.minimum">0.8em</xsl:attribute>
		<xsl:attribute name="space-after.optimum">1em</xsl:attribute>
		<xsl:attribute name="space-after.maximum">1.2em</xsl:attribute>
		<xsl:attribute name="margin-left">0in</xsl:attribute>
		<xsl:attribute name="margin-right">0in</xsl:attribute>
	</xsl:attribute-set>

	<!-- Márgenes entre los índices -->
	<xsl:attribute-set name="toc.margin.properties">
		<xsl:attribute name="space-before.minimum">2em</xsl:attribute>
		<xsl:attribute name="space-before.optimum">2em</xsl:attribute>
		<xsl:attribute name="space-before.maximum">2em</xsl:attribute>
		<xsl:attribute name="space-after.minimum">2em</xsl:attribute>
		<xsl:attribute name="space-after.optimum">2em</xsl:attribute>
		<xsl:attribute name="space-after.maximum">2em</xsl:attribute>
	</xsl:attribute-set>

	<!-- Título de la tabla de contenidos -->
	<xsl:template name="table.of.contents.titlepage" priority="1">
		<fo:block font-size="16pt" font-family="Georgia"
			space-before="0in" space-before.conditionality="retain"
			space-after="20pt" padding-top="40pt"
			padding-left="15pt" padding-bottom="0pt">
			<xsl:call-template name="gentext">
				<xsl:with-param name="key" select="'TableofContents'" />
			</xsl:call-template>
		</fo:block>
	</xsl:template>

	<xsl:template name="list.of.figures.titlepage" priority="1">
		<fo:block font-size="16pt" font-family="Georgia"
			space-before="0in" space-before.conditionality="retain"
			space-after="20pt" padding-top="40pt"
			padding-left="15pt" padding-bottom="0pt">
			<xsl:call-template name="gentext">
				<xsl:with-param name="key" select="'ListofFigures'" />
			</xsl:call-template>
		</fo:block>
	</xsl:template>

	<!-- INDICES -->

	<!-- Para quitar el índice y lista de figuras -->
	<!-- <xsl:param name="generate.toc" />-->

	<xsl:attribute-set name="toc.line.properties">
		
		<xsl:attribute name="font-family">
			<xsl:choose>
				<xsl:when test="self::section">Calibri</xsl:when>
				<xsl:otherwise>Georgia</xsl:otherwise>
			</xsl:choose>
		</xsl:attribute>
		<xsl:attribute name="padding-top">
			<xsl:choose>
				<xsl:when test="self::chapter">20pt</xsl:when>
				<xsl:otherwise>2pt</xsl:otherwise>
			</xsl:choose>
		</xsl:attribute>
		<xsl:attribute name="border-bottom">
			<xsl:choose>
				<xsl:when test="self::chapter">
					0.5pt solid black
				</xsl:when>
				<xsl:otherwise>0pt solid black</xsl:otherwise>
			</xsl:choose>
		</xsl:attribute>
		<xsl:attribute name="font-size">11</xsl:attribute>
	</xsl:attribute-set>

	<!-- Separador del índice entre el título y la numeración -->
	<xsl:param name="autotoc.label.separator">.-</xsl:param>
	<xsl:param name="generate.section.toc.level" select="1"></xsl:param>

	<!-- Genera las entradas del índice. Sólo está cambiado puntos por espacios
-->
	<xsl:template name="toc.line">
		<xsl:param name="toc-context" select="NOTANODE" />

		<xsl:variable name="id">
			<xsl:call-template name="object.id" />
		</xsl:variable>

		<xsl:variable name="label">
			<xsl:apply-templates select="." mode="label.markup" />
		</xsl:variable>

		<fo:block xsl:use-attribute-sets="toc.line.properties">
			<fo:inline keep-with-next.within-line="always">
				<fo:basic-link internal-destination="{$id}">
					<xsl:if test="$label != ''">
						<xsl:copy-of select="$label" />
						<xsl:value-of select="$autotoc.label.separator" />
					</xsl:if>
					<xsl:apply-templates select="."
						mode="titleabbrev.markup" />				
				</fo:basic-link>
			</fo:inline>
			<fo:inline keep-together.within-line="always">
				<xsl:text></xsl:text>
				<fo:leader leader-pattern="space"
					leader-pattern-width="0cm" leader-alignment="reference-area"
					keep-with-next.within-line="always" />
				<xsl:text></xsl:text>
				<fo:basic-link internal-destination="{$id}">
					<fo:page-number-citation ref-id="{$id}" />
				</fo:basic-link>
			</fo:inline>
		</fo:block>
	</xsl:template>
</xsl:stylesheet>

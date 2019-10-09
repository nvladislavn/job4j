<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" encoding="utf-8"/>

    <xsl:template match="/">
        <entries>
            <xsl:apply-templates/>
        </entries>
    </xsl:template>
    <xsl:template match="entries/entry">
        <xsl:element name="{name()}">
            <xsl:attribute name="field">
                <xsl:value-of select="substring-before(substring-after(., '&#10;        '), '&#10;    ')"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
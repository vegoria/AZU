<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Moja Klasa</h2>
 
Klasa: <xsl:value-of select="klasa/numer"/>
<xsl:value-of select="klasa/litera"/><br/>
WYCHOWAWCA: <br/>
Imie: <xsl:value-of select="klasa/wychowawca/imie"/><br/>
Nazwisko: <xsl:value-of select="klasa/wychowawca/nazwisko"/><br/>
Data urodzenia: <xsl:value-of select="klasa/wychowawca/rokUrodzenia"/> <br/>
UCZNIOWIE: <br/>

  <table border="1">
    <tr bgcolor="#9acd32">
      <th style="text-align:left">Imie</th>
      <th style="text-align:left">Nazwisko</th>
      <th style="text-align:left">Rok urodzenia</th>
    </tr>
    <xsl:for-each select="klasa/uczniowie/uczen">
    <tr>
      <td><xsl:value-of select="imie"/></td>
      <td><xsl:value-of select="nazwisko"/></td>
      <td><xsl:value-of select="rokUrodzenia"/></td>
    </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>


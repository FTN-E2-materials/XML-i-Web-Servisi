package main

import (
	"fmt"
	"os"
	"strconv"

	"github.com/antchfx/xmlquery"
	"github.com/antchfx/xpath"
)

func main() {

	fmt.Println("------------------------------------------ LIGA -------------------------------------------------------")
	// Ucitati XML dokument iz fajla.
	f1, err := os.Open(`liga.xml`)
	if err != nil {
		panic(err)
	}

	// Parsirati XML dokument.
	doc1, err := xmlquery.Parse(f1)
	if err != nil {
		panic(err)
	}

	// Zadatak: Izbrojati sve klubove u dokumentu.
	expr, err := xpath.Compile("count(//klub)")
	if err != nil {
		panic(err)
	}
	fmt.Printf("Broj klubova: %f\n", expr.Evaluate(xmlquery.CreateXPathNavigator(doc1)).(float64))

	// Zadatak: Ispisati nazive svih klubova.
	fmt.Println("Klubovi:")
	klubovi := xmlquery.Find(doc1, "//klub")
	for index, klub := range klubovi {
		if nazivKluba := klub.SelectElement("naziv"); nazivKluba != nil {
			rbr := index + 1
			fmt.Printf("%d: %s\n", rbr, nazivKluba.InnerText())
		}
	}

	// Zadatak: Ispisati broj asistencija po utakmici za svakog Barseloninog igraca.
	barsiniIgraci, error := xmlquery.QueryAll(doc1, "//klub[naziv = 'Barselona']//igrac")
	if error != nil {
		panic(error)
	}
	for _, igrac := range barsiniIgraci {
		fmt.Printf("Barselonin igrac: %s %s - apg: %s\n", xmlquery.FindOne(igrac, "ime").InnerText(), xmlquery.FindOne(igrac, "prezime").InnerText(), xmlquery.FindOne(igrac, "apg").InnerText())
	}

	// Zadatak: Pronaci drugi klub i ispisati njegov naziv.
	klub2 := xmlquery.FindOne(doc1, "//klub[2]")
	if naziv := klub2.SelectElement("naziv"); naziv != nil {
		fmt.Printf("Naziv drugog kluba je: %s\n", naziv.InnerText())
	}

	// Zadatak: Ispisati broj pobeda za Barselonu.
	barsa, err := xmlquery.Query(doc1, "//klub[naziv = 'Barselona']")
	fmt.Printf("Barselona: %s \n", barsa.SelectElement("brojpobeda").InnerText())

	// Zadatak: Ispisati imena i prezimena svih igraca u dokumentu zajedno sa njihovim "id" atributima.
	fmt.Println("Svi igraci:")
	for _, n := range xmlquery.Find(doc1, "//igrac") {
		fmt.Printf("%s: %s %s \n", n.SelectAttr("id"), xmlquery.FindOne(n, "ime").InnerText(), xmlquery.FindOne(n, "prezime").InnerText())
	}

	// Zadatak: Ispisati imena, prezimena i broj asistencija po utakmici za one igrace koji imaju u proseku vise od 5 asistencija po utakmici.
	fmt.Println("Svi igraci koji imaju vise od 5 asistencija po utakmici:")
	for _, n := range xmlquery.Find(doc1, "//igrac[apg > 5]") {
		fmt.Printf("%s: %s %s - %s \n", n.SelectAttr("id"), xmlquery.FindOne(n, "ime").InnerText(), xmlquery.FindOne(n, "prezime").InnerText(), xmlquery.FindOne(n, "apg").InnerText())
	}

	// Zadatak: Pronaci igraca ciji je id = igrac10 i ispisati ceo pronadjeni XML element.
	igrac10 := xmlquery.FindOne(doc1, "//igrac[@id = 'igrac10']")
	fmt.Printf("Id atribut - igrac10: %s \n", igrac10.OutputXML(true))

	// Zadatak: Izracunati ukupan broj poena po utakmici za svaki klub.
	for _, klub := range xmlquery.Find(doc1, "//klub") {
		var ukupanRezultat float64 = 0
		for _, igrac := range klub.SelectElements("//igrac") {
			ppgBr, _ := strconv.ParseFloat(igrac.SelectElement("ppg").InnerText(), 64)
			ukupanRezultat += ppgBr
		}
		fmt.Printf("%s: %f \n", klub.SelectElement("naziv").InnerText(), ukupanRezultat)
	}

	// Zadatak: Ispisati igrace nize od 200 cm.
	igraci, error := xmlquery.QueryAll(doc1, "//igrac[visina < 200]")
	if error != nil {
		panic(error)
	}
	for _, igrac := range igraci {
		fmt.Printf("%s %s - visok %s \n", xmlquery.FindOne(igrac, "ime").InnerText(), xmlquery.FindOne(igrac, "prezime").InnerText(), xmlquery.FindOne(igrac, "visina").InnerText())
	}

	fmt.Println("------------------------------------------ PRODAVNICA KNJIGA -------------------------------------------------------")
	// Ucitati XML dokument iz fajla.
	f2, err := os.Open(`prodavnica_knjiga.xml`)
	if err != nil {
		panic(err)
	}

	// Parsirati XML dokument.
	doc2, err := xmlquery.Parse(f2)
	if err != nil {
		panic(err)
	}

	// Zadatak: Izracunati ukupnu vrednost dostupnih knjiga u prodavnici.
	sumExpr, err := xpath.Compile("sum(//knjiga[@dostupna = 'true']//cena)")
	if err != nil {
		panic(err)
	}
	fmt.Printf("Ukupna vrednost dostupnih knjiga u prodavnici: %f\n", sumExpr.Evaluate(xmlquery.CreateXPathNavigator(doc2)).(float64))

	fmt.Println("------------------------------------------ ZAVRSNI RAD -------------------------------------------------------")
	// Ucitati XML dokument iz fajla.
	f3, err := os.Open(`zavrsni_rad.xml`)
	if err != nil {
		panic(err)
	}
	// Parsirati XML dokument.
	doc3, err := xmlquery.Parse(f3)
	if err != nil {
		panic(err)
	}

	// Zadatak: Izlistati sve autore rada.
	fmt.Println("Autori: ")
	for _, autor := range xmlquery.Find(doc3, "//autor") {
		fmt.Printf("%s %s \n", xmlquery.FindOne(autor, "ime").InnerText(), xmlquery.FindOne(autor, "prezime").InnerText())
	}

	// Zadatak: Izracunati ukupan broj poglavlja u radu.
	countExpr, err := xpath.Compile("count(//poglavlje)")
	brPoglavlja := countExpr.Evaluate(xmlquery.CreateXPathNavigator(doc3)).(float64)
	fmt.Printf("Ukupan broj poglavlja: %f\n", brPoglavlja)

	fmt.Println("Sva poglavlja: ")
	// Zadatak: Izlistati naslove svih poglavlja u radu.
	for _, poglavlje := range xmlquery.Find(doc3, "//poglavlje") {
		fmt.Printf("Naslov poglavlja: %s\n", xmlquery.FindOne(poglavlje, "naslov").InnerText())
	}

	// Zadatak: Izlistati naslove svih poglavlja koja poseduju potpoglavlja.
	fmt.Println("Samo ona poglavlja koja poseduju potpoglavlja: ")
	for _, poglavlje := range xmlquery.Find(doc3, "//poglavlje") {
		potpoglavlja := poglavlje.SelectElements("poglavlje")
		if len(potpoglavlja) > 0 {
			fmt.Printf("Naslov poglavlja: %s\n", xmlquery.FindOne(poglavlje, "naslov").InnerText())
		}
	}
}

package main

import (
	"encoding/xml"
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

type Rad struct {
	XMLName        xml.Name       `xml:"rad"`
	VrstaRada      string         `xml:"vrsta_rada,attr"`
	NaslovnaStrana NaslovnaStrana `xml:"naslovna_strana"`
	Sadrzaj        Sadrzaj        `xml:"sadrzaj"`
	Poglavlja      Poglavlja      `xml:"poglavlja"`
}

type NaslovnaStrana struct {
	Institucija Institucija `xml:"institucija"`
	Autor       Autor       `xml:"autor"`
	TemeRada    []TemaRada  `xml:"tema_rada"`
	NivoStudija string      `xml:"nivo_studija"`
}

type Institucija struct {
	Univerzitet string `xml:"univerzitet"`
	Fakultet    string `xml:"fakultet"`
	Departman   string `xml:"departman"`
	Katedra     string `xml:"katedra"`
}

type Autor struct {
	Ime         string `xml:"ime"`
	Prezime     string `xml:"prezime"`
	BrojIndeksa string `xml:"broj_indeksa"`
}

type TemaRada struct {
	Jezik string `xml:"jezik,attr"`
	Tema  string `xml:",chardata"`
}

type Sadrzaj struct {
	Stavke []Stavka `xml:"stavka"`
}

type Stavka struct {
	NaslovPoglavlja string `xml:"naslov_poglavlja"`
	BrojStrane      string `xml:"broj_strane"`
}

type Poglavlja struct {
	Poglavlja []Poglavlje `xml:"poglavlje"`
}

type Poglavlje struct {
	Naslov    string      `xml:"naslov"`
	Pasus     []Pasus     `xml:"pasus"`
	Poglavlja []Poglavlje `xml:"poglavlje"`
}

type Pasus struct {
	SadrzajPasusa string `xml:",chardata"`
	Slika         Slika  `xml:"slika"`
	Lista         Lista  `xml:"lista"`
	Tabela        Tabela `xml:"tabela"`
}

type Lista struct {
	Vrsta       string   `xml:"vrsta,attr,omitempty"`
	StavkeListe []string `xml:"stavka,omitempty"`
}

type Tabela struct {
	Redovi []Red `xml:"red"`
}

type Red struct {
	Celije []string `xml:"celija"`
}

type Slika struct {
	Natpis  string `xml:"natpis,omitempty"`
	Sadrzaj string `xml:"sadrzaj,omitempty"`
}

func prikaziTeme(naslovnaStrana NaslovnaStrana) {
	fmt.Print("\n\t Teme:")
	for i, t := range naslovnaStrana.TemeRada {
		fmt.Print("\n\t\t" + strconv.Itoa(i+1) + "." + " Naslov - " + t.Tema + " (Jezik - " + t.Jezik + ")")
	}
}

func prikaziStavkeSadrzaja(sadrzaj Sadrzaj) {
	for i, s := range sadrzaj.Stavke {
		fmt.Print("\n\t" + strconv.Itoa(i+1) + "." + " Broj strane - " + s.BrojStrane + " Naslov poglavlja - " + s.NaslovPoglavlja)
	}
}

func stampajTabove(number int) {
	for i := 0; i < number; i++ {
		fmt.Print("\t")
	}
}

func prikaziParagrafe(poglavlja []Poglavlje, level int) {
	for _, p := range poglavlja {
		fmt.Print("\n")
		stampajTabove(level + 1)
		fmt.Print("Poglavlje:")
		fmt.Print("\n")
		stampajTabove(level + 1)
		fmt.Print("----------")
		fmt.Print("\n")
		stampajTabove(level + 2)
		fmt.Print("Naslov poglavlja - " + p.Naslov)
		stampajTabove(level + 2)
		fmt.Print("\n")
		stampajTabove(level + 2)
		fmt.Print("Pasusi:")
		prikaziPasuse(p.Pasus, level)
		prikaziParagrafe(p.Poglavlja, level+1)
	}
}

func prikaziPasuse(pasusi []Pasus, level int) {
	for i, pasus := range pasusi {
		fmt.Print("\n")
		stampajTabove(level + 3)
		fmt.Print("Pasus " + strconv.Itoa(i+1) + ":")
		fmt.Print("\n")
		stampajTabove(level + 4)
		fmt.Print("Sadržaj - " + strings.Trim(strings.Trim(pasus.SadrzajPasusa, "\n"), " "))
		if pasus.Slika.Natpis != "" {
			stampajTabove(level + 4)
			fmt.Print("Slika (natpis) - " + pasus.Slika.Natpis)
		}

		if len(pasus.Lista.StavkeListe) != 0 {
			fmt.Print("\n")
			stampajTabove(level + 4)
			fmt.Print("Lista (vrsta - " + pasus.Lista.Vrsta + "):")
			fmt.Print("\n")
			stampajTabove(level + 5)
			fmt.Print("Stavke liste:")
			for i, stavka := range pasus.Lista.StavkeListe {
				fmt.Print("\n")
				stampajTabove(level + 6)
				fmt.Print(strconv.Itoa(i+1) + "." + stavka)
			}
		}

		if len(pasus.Tabela.Redovi) != 0 {
			fmt.Print("\n")
			stampajTabove(level + 4)
			fmt.Print("Tabela:")
			for _, red := range pasus.Tabela.Redovi {
				fmt.Print("\n")
				stampajTabove(level + 5)
				for _, celija := range red.Celije {
					fmt.Print(celija + " | ")
				}
			}
		}
	}
}

func prikaziRad(rad Rad) {
	fmt.Println("\n RAD (vrsta rada - " + rad.VrstaRada + ")")
	fmt.Print("------------------")
	fmt.Println("\n Naslovna strana:")
	fmt.Print("\t Institucija:" +
		"\n\t\t Univerzitet - " + rad.NaslovnaStrana.Institucija.Univerzitet +
		"\n\t\t Fakultet - " + rad.NaslovnaStrana.Institucija.Fakultet +
		"\n\t\t Departman - " + rad.NaslovnaStrana.Institucija.Departman +
		"\n\t\t Katedra - " + rad.NaslovnaStrana.Institucija.Katedra)
	fmt.Print("\n\t Autor:" +
		"\n\t\t Ime - " + rad.NaslovnaStrana.Autor.Ime +
		"\n\t\t Prezime - " + rad.NaslovnaStrana.Autor.Prezime +
		"\n\t\t Broj indeksa - " + rad.NaslovnaStrana.Autor.BrojIndeksa)
	prikaziTeme(rad.NaslovnaStrana)
	fmt.Print("\n\t Nivo studija - " + rad.NaslovnaStrana.NivoStudija)
	fmt.Print("\n Sadržaj:")
	prikaziStavkeSadrzaja(rad.Sadrzaj)
	fmt.Print("\n Poglavlja:")
	prikaziParagrafe(rad.Poglavlja.Poglavlja, 0)
}

func main() {

	// Otvaramo XML fajl
	xmlFile, err := os.Open("../zavrsni_rad.xml")
	// Ako je doslo do greske prilikom otvaranja fajla, prikazi error
	if err != nil {
		fmt.Println(err)
	}

	fmt.Println("Fajl zavrsni_rad.xml je uspesno otvoren")
	// Zatvori fajl kada se funkcija main izvrsi (na njenom kraju, kasnije ne mislimo o ovome)
	defer xmlFile.Close()

	// Citaj otvoren fajl kao niz bajtova
	byteValue, _ := ioutil.ReadAll(xmlFile)

	// Promenljiva tipa Rad koja ce sadrzati parsirani XML dokument
	var rad Rad

	// Unmarshal - parsiramo XML u rad
	xml.Unmarshal(byteValue, &rad)

	prikaziRad(rad)

	// Menjamo vrstu rada - menjamo podatke ucitane iz XML-a
	rad.VrstaRada = "Seminarski"

	// Ucitavamo izmenjen rad (menjali smo podatke) u xml
	output, err := xml.MarshalIndent(&rad, "  ", "    ")
	if err != nil {
		fmt.Printf("error: %v\n", err)
	}

	// Kreiramo xml fajl na osnovu xml sadrzaja generisanog putem MarshalIndent metode na osnovu rada
	_ = ioutil.WriteFile("generisani.xml", output, 0644)
}

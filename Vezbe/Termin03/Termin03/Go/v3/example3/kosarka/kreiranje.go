package kosarka

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"time"

	"github.com/LazarJovic/vezbe/v3/example3/etree"
)

func ObradaUnosa(unos string) string {
	return unos[:len(unos)-2]
}

func KreirajKlub() Klub {
	var klub Klub
	reader := bufio.NewReader(os.Stdin)
	fmt.Println("Kako biste kreirali klub pratite uputstva :)")

	fmt.Print("Naziv kluba: ")
	nazivKluba, _ := reader.ReadString('\n')
	klub.Naziv = ObradaUnosa(nazivKluba)

	fmt.Print("Grad: ")
	grad, _ := reader.ReadString('\n')
	klub.Lokacija.Grad = ObradaUnosa(grad)

	fmt.Print("Drzava: ")
	drzava, _ := reader.ReadString('\n')
	klub.Lokacija.Drzava = ObradaUnosa(drzava)

	fmt.Print("Naziv arene: ")
	nazivArene, _ := reader.ReadString('\n')
	klub.Arena.Naziv = ObradaUnosa(nazivArene)

	fmt.Print("Kapacitet arene: ")
	fmt.Scan(&klub.Arena.Kapacitet)

	return klub
}

func KreirajIgraca() (Igrac, string) {
	var igrac Igrac
	reader := bufio.NewReader(os.Stdin)
	fmt.Println("Kako biste kreirali igraca pratite uputstva :)")

	fmt.Print("Ime: ")
	ime, _ := reader.ReadString('\n')
	igrac.Ime = ObradaUnosa(ime)

	fmt.Print("Prezime: ")
	prezime, _ := reader.ReadString('\n')
	igrac.Prezime = ObradaUnosa(prezime)

	fmt.Print("Datum rodjenja (yyyy-mm-dd):")
	datumRodjenja, _ := reader.ReadString('\n')
	obradjenDatumRodjenja := ObradaUnosa(datumRodjenja)
	_, errDatum := time.Parse("2006-01-02", obradjenDatumRodjenja)
	if errDatum != nil {
		return igrac, "Datum rodjenja nije validno unet. Mora biti formata yyyy-mm-dd"
	}
	igrac.DatumRodjenja = obradjenDatumRodjenja

	fmt.Print("Mesto rodjenja: ")
	mesto, _ := reader.ReadString('\n')
	igrac.MestoRodjenja.Grad = ObradaUnosa(mesto)

	fmt.Print("Drzava rodjenja: ")
	drzava, _ := reader.ReadString('\n')
	igrac.MestoRodjenja.Drzava = ObradaUnosa(drzava)

	fmt.Print("Visina (u cm): ")
	var _, errVisina = fmt.Scan(&igrac.Visina.Vrednost)
	if errVisina != nil {
		return igrac, "Visina ne predstavlja validan unos! Mora biti uneta kao ceo broj u centimetrima."
	}
	igrac.Visina.NazivJedinice = "cm"

	fmt.Print("Tezina (u kg): ")
	var _, errTezina = fmt.Scan(&igrac.Tezina.Vrednost)
	if errTezina != nil {
		return igrac, "Tezina ne predstavlja validan unos! Mora biti uneta kao ceo broj u kilogramima."
	}
	igrac.Tezina.NazivJedinice = "kg"

	fmt.Print("Pozicija (1 - plejmejker, 2 - bek suter, 3 - krilo, 4 - krilni centar, 5 - centar): ")
	fmt.Scan(&igrac.Pozicija)
	if igrac.Pozicija != 1 && igrac.Pozicija != 2 && igrac.Pozicija != 3 && igrac.Pozicija != 4 && igrac.Pozicija != 5 {
		return igrac, "Pozicija mora biti uneta kao jedan od narednih pet brojeva: \n" +
			" 1 - Plejmejker \n 2 - Bek suter \n 3 - Krilo \n 4 - Krilni centar \n 5 - Centar"
	}
	return igrac, "ok"
}

func KreirajKlubXML(klub Klub) {
	doc := etree.NewDocument()
	doc.CreateProcInst("xml", `version="1.0" encoding="UTF-8"`)

	// root
	club := doc.CreateElement("klub")
	club.CreateComment("Podaci vezani za klub")

	naziv := club.CreateElement("naziv")
	naziv.CreateCharData(klub.Naziv)

	lokacija := club.CreateElement("lokacija")
	grad := lokacija.CreateElement("grad")
	grad.CreateCharData(klub.Lokacija.Grad)
	drzava := lokacija.CreateElement("drzava")
	drzava.CreateCharData(klub.Lokacija.Drzava)

	arena := club.CreateElement("arena")
	nazivArene := arena.CreateElement("naziv")
	nazivArene.CreateCharData(klub.Arena.Naziv)
	kapacitet := arena.CreateElement("kapacitet")
	kapacitet.CreateCharData(strconv.Itoa(klub.Arena.Kapacitet))

	doc.Indent(2)

	err := doc.WriteToFile("klubovi/" + klub.Naziv + ".xml")
	if err != nil {
		log.Println(err.Error())
	}
}

func KreirajIgracaXML(igrac Igrac) {
	doc := etree.NewDocument()
	doc.CreateProcInst("xml", `version="1.0" encoding="UTF-8"`)

	root := doc.CreateElement("igrac")
	root.CreateComment("Podaci vezani za igraca")

	ime := root.CreateElement("ime")
	ime.CreateCharData(igrac.Ime)

	prezime := root.CreateElement("prezime")
	prezime.CreateCharData(igrac.Prezime)

	datumRodjenja := root.CreateElement("datumrodjenja")
	datumRodjenja.CreateCharData(igrac.DatumRodjenja)

	mestoRodjenja := root.CreateElement("lokacija")
	grad := mestoRodjenja.CreateElement("grad")
	grad.CreateCharData(igrac.MestoRodjenja.Grad)
	drzava := mestoRodjenja.CreateElement("drzava")
	drzava.CreateCharData(igrac.MestoRodjenja.Drzava)

	visina := root.CreateElement("visina")
	visina.CreateCharData(strconv.Itoa(igrac.Visina.Vrednost))
	visina.CreateAttr("jedinica", igrac.Visina.NazivJedinice)

	tezina := root.CreateElement("tezina")
	tezina.CreateCharData(strconv.Itoa(igrac.Tezina.Vrednost))
	tezina.CreateAttr("jedinica", igrac.Tezina.NazivJedinice)

	pozicija := root.CreateElement("pozicija")
	switch igrac.Pozicija {
	case 1:
		pozicija.CreateCharData("Plejmejker")
	case 2:
		pozicija.CreateCharData("Bek suter")
	case 3:
		pozicija.CreateCharData("Krilo")
	case 4:
		pozicija.CreateCharData("Krilni centar")
	case 5:
		pozicija.CreateCharData("Centar")
	}

	doc.Indent(2)

	err := doc.WriteToFile("igraci/" + igrac.Ime + igrac.Prezime + ".xml")
	if err != nil {
		log.Println(err.Error())
	}
}

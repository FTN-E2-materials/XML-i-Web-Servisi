package kosarka

import (
	"bufio"
	"fmt"
	"log"
	"os"

	"github.com/LazarJovic/vezbe/v3/example3/etree"
)

func PrikaziKlub() {
	reader := bufio.NewReader(os.Stdin)

	fmt.Print("Unesite naziv kluba za prikaz: ")
	nazivKluba, err := reader.ReadString('\n')
	if err != nil {
		log.Println(err.Error())
	}
	doc := etree.NewDocument()
	if err := doc.ReadFromFile("klubovi/" + ObradaUnosa(nazivKluba) + ".xml"); err != nil {
		panic(err)
	}
	klub := doc.SelectElement("klub")
	fmt.Print("\nKlub")
	fmt.Print("\n-----")
	fmt.Println("\nNaziv: " + klub.SelectElement("naziv").Text())
	fmt.Println("\nLokacija: ")
	fmt.Print("\t Grad: " + klub.SelectElement("lokacija").SelectElement("grad").Text())
	fmt.Print("\n\t Drzava: " + klub.SelectElement("lokacija").SelectElement("drzava").Text())
	fmt.Println("\nArena: ")
	fmt.Print("\t Naziv arene: " + klub.SelectElement("arena").SelectElement("naziv").Text())
	fmt.Print("\n\t Kapacitet: " + klub.SelectElement("arena").SelectElement("kapacitet").Text())
	fmt.Print("\n\n")
}

func PrikaziIgraca() {
	reader := bufio.NewReader(os.Stdin)

	fmt.Print("Unesite ime igraca: ")
	imeIgraca, err := reader.ReadString('\n')
	if err != nil {
		log.Println(err.Error())
	}
	fmt.Print("Unesite prezime igraca: ")
	prezimeIgraca, err := reader.ReadString('\n')
	if err != nil {
		log.Println(err.Error())
	}
	doc := etree.NewDocument()
	if err := doc.ReadFromFile("igraci/" + ObradaUnosa(imeIgraca) + ObradaUnosa(prezimeIgraca) + ".xml"); err != nil {
		panic(err)
	}

	igrac := doc.SelectElement("igrac")
	fmt.Print("\nIgrac")
	fmt.Print("\n-----")
	fmt.Print("\nIme: " + igrac.SelectElement("ime").Text())
	fmt.Print("\nPrezime: " + igrac.SelectElement("prezime").Text())
	fmt.Print("\nDatum rodjenja: " + igrac.SelectElement("datumrodjenja").Text())
	fmt.Println("\nLokacija: ")
	fmt.Print("\t Mesto rodjenja: " + igrac.SelectElement("lokacija").SelectElement("grad").Text())
	fmt.Print("\n\t Drzava: " + igrac.SelectElement("lokacija").SelectElement("drzava").Text())
	fmt.Print("\nVisina: " + igrac.SelectElement("visina").Text() + " " + igrac.SelectElement("visina").SelectAttrValue("jedinica", "unknown"))
	fmt.Print("\nTezina: " + igrac.SelectElement("tezina").Text() + " " + igrac.SelectElement("tezina").SelectAttrValue("jedinica", "unknown"))
	fmt.Print("\nPozicija: " + igrac.SelectElement("pozicija").Text())
	fmt.Print("\n\n")
}

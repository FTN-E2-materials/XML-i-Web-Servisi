package main

import (
	"bufio"
	"fmt"
	"os"

	"github.com/LazarJovic/vezbe/v3/example3/kosarka"
)

func main() {

	for {
		fmt.Print("Odaberite opciju: \n 0 - unos kluba, 1 - unos igraca, 2 - prikaz kluba, 3 prikaz igraca 4 - izlaz \n")
		reader := bufio.NewReader(os.Stdin)
		input, _, err := reader.ReadRune()
		if err != nil {
			fmt.Println("An error occured while reading input. Please try again", err)
			return
		}

		switch input {
		case '0':
			klub := kosarka.KreirajKlub()
			kosarka.KreirajKlubXML(klub)
		case '1':
			igrac, err := kosarka.KreirajIgraca()
			if err != "ok" {
				fmt.Println("\n" + err + "\n")
				break
			}
			kosarka.KreirajIgracaXML(igrac)
		case '2':
			kosarka.PrikaziKlub()
		case '3':
			kosarka.PrikaziIgraca()
		}

		if input == '4' {
			break
		}
	}
}

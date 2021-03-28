package kosarka

type Lokacija struct {
	Grad   string
	Drzava string
}

type Arena struct {
	Naziv     string
	Kapacitet int
}

type Klub struct {
	Naziv    string
	Lokacija Lokacija
	Arena    Arena
}

type Mera struct {
	NazivJedinice string
	Vrednost      int
}

type Pozicija int

const (
	Plejmejker = iota
	BekSuter
	Krilo
	KrilniCentar
	Centar
)

type Igrac struct {
	Ime           string
	Prezime       string
	DatumRodjenja string
	MestoRodjenja Lokacija
	Visina        Mera
	Tezina        Mera
	Pozicija      Pozicija
}

$folders = Import-Csv ..\folders.csv

foreach ($i in $folders) {
    New-Item -Path ..\LegalBot\lawFiles\$($i.Cases.Replace('/','-')) -ItemType Directory
    #$i.Cases
}

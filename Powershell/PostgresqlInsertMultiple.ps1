$data = Import-Csv -Path .\Data.csv

foreach ($i in $data){
    "('$($i.name)',$($i.DNI),'$($i.address)','$($i.email)')," >> values.txt


}
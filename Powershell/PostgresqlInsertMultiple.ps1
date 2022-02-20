$data = Import-Csv -Path .\Git\Portafolio\Powershell\Data.csv

foreach ($i in $data){
    "('$($i.Cases)',$($i.DNI),'$($i.Comment)','$($i.url)','$($i.date)')," >> values.txt


}
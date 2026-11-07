package com.majician.app.data

data class CountryMcc(
    val countryName: String,
    val flag: String,
    val carrierName: String,
    val mccMnc: String,
    val mcc: String,
    val mnc: String
)

data class CityLocation(
    val cityName: String,
    val countryName: String,
    val latitude: Double,
    val longitude: Double,
    val isCustom: Boolean = false
)

object LocationRepository {
    val countryMccs = listOf(
        // Custom Input Option
        CountryMcc("Custom Code", "⚡", "Input custom numerical MCC/MNC", "CUSTOM", "", ""),

        // Asia
        CountryMcc("India", "🇮🇳", "Jio", "405854", "405", "854"),
        CountryMcc("India", "🇮🇳", "Airtel", "40410", "404", "10"),
        CountryMcc("India", "🇮🇳", "Vodafone Idea", "40420", "404", "20"),
        CountryMcc("Sri Lanka", "🇱🇰", "Dialog", "41302", "413", "02"),
        CountryMcc("Sri Lanka", "🇱🇰", "Mobitel", "41301", "413", "01"),
        CountryMcc("Sri Lanka", "🇱🇰", "Airtel", "41305", "413", "05"),
        CountryMcc("Sri Lanka", "🇱🇰", "Hutch", "41308", "413", "08"),
        CountryMcc("Pakistan", "🇵🇰", "Jazz", "41001", "410", "01"),
        CountryMcc("Pakistan", "🇵🇰", "Telenor", "41006", "410", "06"),
        CountryMcc("Pakistan", "🇵🇰", "Zong", "41004", "410", "04"),
        CountryMcc("Bangladesh", "🇧🇩", "Grameenphone", "47001", "470", "01"),
        CountryMcc("Bangladesh", "🇧🇩", "Robi", "47002", "470", "02"),
        CountryMcc("Indonesia", "🇮🇩", "Telkomsel", "51010", "510", "10"),
        CountryMcc("Indonesia", "🇮🇩", "Indosat", "51001", "510", "01"),
        CountryMcc("Indonesia", "🇮🇩", "XL Axiata", "51011", "510", "11"),
        CountryMcc("Malaysia", "🇲🇾", "Maxis", "50212", "502", "12"),
        CountryMcc("Malaysia", "🇲🇾", "CelcomDigi", "50219", "502", "19"),
        CountryMcc("Philippines", "🇵🇭", "Globe", "51502", "515", "02"),
        CountryMcc("Philippines", "🇵🇭", "Smart", "51503", "515", "03"),
        CountryMcc("Vietnam", "🇻🇳", "Viettel", "45204", "452", "04"),
        CountryMcc("Vietnam", "🇻🇳", "Vinaphone", "45202", "452", "02"),
        CountryMcc("Thailand", "🇹🇭", "AIS", "52001", "520", "01"),
        CountryMcc("Thailand", "🇹🇭", "TrueMove", "52000", "520", "00"),
        CountryMcc("Japan", "🇯🇵", "NTT Docomo", "44010", "440", "10"),
        CountryMcc("Japan", "🇯🇵", "SoftBank", "44020", "440", "20"),
        CountryMcc("Japan", "🇯🇵", "KDDI", "44050", "440", "50"),
        CountryMcc("South Korea", "🇰🇷", "SK Telecom", "45005", "450", "05"),
        CountryMcc("South Korea", "🇰🇷", "KT", "45008", "450", "08"),
        CountryMcc("China", "🇨🇳", "China Mobile", "46000", "460", "00"),
        CountryMcc("China", "🇨🇳", "China Unicom", "46001", "460", "01"),
        CountryMcc("Singapore", "🇸🇬", "Singtel", "52501", "525", "01"),
        CountryMcc("Singapore", "🇸🇬", "StarHub", "52505", "525", "05"),

        // Africa
        CountryMcc("Nigeria", "🇳🇬", "MTN", "62130", "621", "30"),
        CountryMcc("Nigeria", "🇳🇬", "Airtel", "62120", "621", "20"),
        CountryMcc("Nigeria", "🇳🇬", "Glo", "62150", "621", "50"),
        CountryMcc("Kenya", "🇰🇪", "Safaricom", "63902", "639", "02"),
        CountryMcc("Kenya", "🇰🇪", "Airtel", "63903", "639", "03"),
        CountryMcc("South Africa", "🇿🇦", "Vodacom", "65501", "655", "01"),
        CountryMcc("South Africa", "🇿🇦", "MTN", "65510", "655", "10"),
        CountryMcc("Egypt", "🇪🇬", "Vodafone", "60202", "602", "02"),
        CountryMcc("Egypt", "🇪🇬", "Orange", "60201", "602", "01"),
        CountryMcc("Ghana", "🇬🇭", "MTN", "62001", "620", "01"),
        CountryMcc("Ghana", "🇬🇭", "Vodafone", "62002", "620", "02"),
        CountryMcc("Ethiopia", "🇪🇹", "Ethio Telecom", "63601", "636", "01"),
        CountryMcc("Morocco", "🇲🇦", "Maroc Telecom", "60401", "604", "01"),
        CountryMcc("Tanzania", "🇹🇿", "Vodacom", "64004", "640", "04"),
        CountryMcc("Uganda", "🇺🇬", "MTN", "64110", "641", "10"),

        // Middle East
        CountryMcc("UAE", "🇦🇪", "e& (Etisalat)", "42402", "424", "02"),
        CountryMcc("UAE", "🇦🇪", "du", "42403", "424", "03"),
        CountryMcc("Saudi Arabia", "🇸🇦", "STC", "42001", "420", "01"),
        CountryMcc("Saudi Arabia", "🇸🇦", "Mobily", "42003", "420", "03"),
        CountryMcc("Qatar", "🇶🇦", "Ooredoo", "42701", "427", "01"),
        CountryMcc("Kuwait", "🇰🇼", "Zain", "41902", "419", "02"),
        CountryMcc("Turkey", "🇹🇷", "Turkcell", "28601", "286", "01"),
        CountryMcc("Oman", "🇴🇲", "Omantel", "42202", "422", "02"),

        // Europe
        CountryMcc("United Kingdom", "🇬🇧", "Vodafone", "23415", "234", "15"),
        CountryMcc("United Kingdom", "🇬🇧", "EE", "23430", "234", "30"),
        CountryMcc("United Kingdom", "🇬🇧", "O2", "23410", "234", "10"),
        CountryMcc("Germany", "🇩🇪", "Telekom", "26201", "262", "01"),
        CountryMcc("Germany", "🇩🇪", "Vodafone", "26202", "262", "02"),
        CountryMcc("Germany", "🇩🇪", "O2", "26203", "262", "03"),
        CountryMcc("France", "🇫🇷", "Orange", "20801", "208", "01"),
        CountryMcc("France", "🇫🇷", "SFR", "20810", "208", "10"),
        CountryMcc("Italy", "🇮🇹", "TIM", "22201", "222", "01"),
        CountryMcc("Italy", "🇮🇹", "Vodafone", "22210", "222", "10"),
        CountryMcc("Spain", "🇪🇸", "Movistar", "21407", "214", "07"),
        CountryMcc("Spain", "🇪🇸", "Vodafone", "21401", "214", "01"),
        CountryMcc("Netherlands", "🇳🇱", "KPN", "20408", "204", "08"),
        CountryMcc("Russia", "🇷🇺", "MTS", "25001", "250", "01"),

        // Americas
        CountryMcc("United States", "🇺🇸", "T-Mobile", "310260", "310", "260"),
        CountryMcc("United States", "🇺🇸", "AT&T", "310410", "310", "410"),
        CountryMcc("United States", "🇺🇸", "Verizon", "311480", "311", "480"),
        CountryMcc("Canada", "🇨🇦", "Rogers", "302720", "302", "720"),
        CountryMcc("Canada", "🇨🇦", "Bell", "302610", "302", "610"),
        CountryMcc("Mexico", "🇲🇽", "Telcel", "334020", "334", "020"),
        CountryMcc("Brazil", "🇧🇷", "Vivo", "72406", "724", "06"),
        CountryMcc("Brazil", "🇧🇷", "Claro", "72405", "724", "05"),
        CountryMcc("Argentina", "🇦🇷", "Personal", "722340", "722", "340"),
        CountryMcc("Colombia", "🇨🇴", "Claro", "732101", "732", "101"),

        // Oceania
        CountryMcc("Australia", "🇦🇺", "Telstra", "50501", "505", "01"),
        CountryMcc("Australia", "🇦🇺", "Optus", "50502", "505", "02"),
        CountryMcc("New Zealand", "🇳🇿", "One NZ", "53001", "530", "01")
    )

    val cityLocations = listOf(
        CityLocation("Custom Coordinates", "⚡ Enter Custom Lat & Lng", 0.0, 0.0, isCustom = true),
        CityLocation("New York", "United States 🇺🇸", 40.7128, -74.0060),
        CityLocation("Los Angeles", "United States 🇺🇸", 34.0522, -118.2437),
        CityLocation("Chicago", "United States 🇺🇸", 41.8781, -87.6298),
        CityLocation("Toronto", "Canada 🇨🇦", 43.6532, -79.3832),
        CityLocation("Vancouver", "Canada 🇨🇦", 49.2827, -123.1207),
        CityLocation("Mexico City", "Mexico 🇲🇽", 19.4326, -99.1332),
        CityLocation("London", "United Kingdom 🇬🇧", 51.5074, -0.1278),
        CityLocation("Paris", "France 🇫🇷", 48.8566, 2.3522),
        CityLocation("Berlin", "Germany 🇩🇪", 52.5200, 13.4050),
        CityLocation("Amsterdam", "Netherlands 🇳🇱", 52.3676, 4.9041),
        CityLocation("Rome", "Italy 🇮🇹", 41.9028, 12.4964),
        CityLocation("Madrid", "Spain 🇪🇸", 40.4168, -3.7038),
        CityLocation("Istanbul", "Turkey 🇹🇷", 41.0082, 28.9784),
        CityLocation("Moscow", "Russia 🇷🇺", 55.7558, 37.6173),
        CityLocation("Tokyo", "Japan 🇯🇵", 35.6762, 139.6503),
        CityLocation("Osaka", "Japan 🇯🇵", 34.6937, 135.5023),
        CityLocation("Seoul", "South Korea 🇰🇷", 37.5665, 126.9780),
        CityLocation("Beijing", "China 🇨🇳", 39.9042, 116.4074),
        CityLocation("Shanghai", "China 🇨🇳", 31.2304, 121.4737),
        CityLocation("Singapore", "Singapore 🇸🇬", 1.3521, 103.8198),
        CityLocation("Bangkok", "Thailand 🇹🇭", 13.7563, 100.5018),
        CityLocation("Kuala Lumpur", "Malaysia 🇲🇾", 3.1390, 101.6869),
        CityLocation("Jakarta", "Indonesia 🇮🇩", -6.2088, 106.8456),
        CityLocation("Manila", "Philippines 🇵🇭", 14.5995, 120.9842),
        CityLocation("Mumbai", "India 🇮🇳", 19.0760, 72.8777),
        CityLocation("New Delhi", "India 🇮🇳", 28.6139, 77.2090),
        CityLocation("Colombo", "Sri Lanka 🇱🇰", 6.9271, 79.8612),
        CityLocation("Kandy", "Sri Lanka 🇱🇰", 7.2906, 80.6337),
        CityLocation("Dubai", "UAE 🇦🇪", 25.2048, 55.2708),
        CityLocation("Riyadh", "Saudi Arabia 🇸🇦", 24.7136, 46.6753),
        CityLocation("Cairo", "Egypt 🇪🇬", 30.0444, 31.2357),
        CityLocation("Lagos", "Nigeria 🇳🇬", 6.5244, 3.3792),
        CityLocation("Nairobi", "Kenya 🇰🇪", -1.2921, 36.8219),
        CityLocation("Johannesburg", "South Africa 🇿🇦", -26.2041, 28.0473),
        CityLocation("Sydney", "Australia 🇦🇺", -33.8688, 151.2093),
        CityLocation("Melbourne", "Australia 🇦🇺", -37.8136, 144.9631),
        CityLocation("Auckland", "New Zealand 🇳🇿", -36.8485, 174.7633),
        CityLocation("Sao Paulo", "Brazil 🇧🇷", -23.5505, -46.6333),
        CityLocation("Buenos Aires", "Argentina 🇦🇷", -34.6037, -58.3816)
    )
}

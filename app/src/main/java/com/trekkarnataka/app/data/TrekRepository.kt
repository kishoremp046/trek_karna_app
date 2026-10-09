package com.trekkarnataka.app.data

/**
 * Static data source. NOTE: elevations/distances are approximate and photo URLs are
 * PLACEHOLDERS (picsum.photos). Replace with real photos and each trek's official
 * booking link before publishing.
 */
object TrekRepository {
    // Default booking portal - replace per trek with the official link.
    private const val BOOK = "https://aranyavihaara.karnataka.gov.in/"

    val districts = listOf(
        "Bagalkote", "Ballari", "Belagavi", "Bengaluru Rural", "Bengaluru Urban", "Bidar", "Chamarajanagar",
        "Chikkaballapur", "Chikkamagaluru", "Chitradurga", "Dakshina Kannada", "Davanagere", "Dharwad", "Gadag",
        "Hassan", "Haveri", "Kalaburagi", "Kodagu", "Kolar", "Koppal", "Mandya", "Mysuru", "Raichur", "Ramanagara",
        "Shivamogga", "Tumakuru", "Udupi", "Uttara Kannada", "Vijayanagara", "Vijayapura", "Yadgir"
    )

    private fun t(name: String, d: String, elev: Int, diff: String, km: Double, dur: String, season: String, desc: String) =
        Trek(
            id = (d + "-" + name).lowercase().replace(" ", "-"), name = name, district = d, elevationM = elev, difficulty = diff,
            distanceKm = km, duration = dur, bestSeason = season, description = desc, bookingUrl = BOOK,
            photos = (1..3).map { "https://picsum.photos/seed/${name.replace(" ", "")}$it/900/600" }
        )

    val treks: List<Trek> = listOf(
        // Kodagu
        t("Tadiandamol", "Kodagu", 1748, "Moderate", 8.0, "4-5 hrs", "Oct - Feb", "Kodagu's highest peak with rolling grasslands, shola forests and sweeping misty views."),
        t("Brahmagiri", "Kodagu", 1608, "Moderate", 12.0, "5-6 hrs", "Oct - Mar", "Trail through Brahmagiri Wildlife Sanctuary to a viewpoint near the Kerala border."),
        t("Kotebetta", "Kodagu", 1620, "Moderate", 7.0, "4 hrs", "Oct - Feb", "Grassy ridge trek with a stunning sunrise and views of the Western Ghats."),
        t("Mandalpatti", "Kodagu", 1050, "Easy", 4.0, "2 hrs", "Sep - Feb", "Short viewpoint trek above the clouds, popular for sunrise."),
        t("Kumara Parvatha", "Kodagu", 1712, "Difficult", 22.0, "2 days", "Oct - Feb", "One of Karnataka's toughest treks via Pushpagiri forests and a rocky final ascent."),
        t("Nishani Motte", "Kodagu", 1200, "Moderate", 6.0, "3 hrs", "Oct - Feb", "Grassland hill trek with wide views over Kodagu's coffee country."),
        // Chikkamagaluru
        t("Mullayanagiri", "Chikkamagaluru", 1930, "Easy", 2.0, "1.5 hrs", "Sep - Feb", "Karnataka's highest peak, reached by a steep stairway with panoramic hill views."),
        t("Kudremukh", "Chikkamagaluru", 1894, "Moderate", 20.0, "7-8 hrs", "Oct - Feb", "Horse-face shaped peak with vast grasslands and forest trail."),
        t("Baba Budangiri", "Chikkamagaluru", 1895, "Easy", 3.0, "2 hrs", "Sep - Feb", "Scenic hill range with a shrine, winding roads and coffee estates."),
        t("Kallathigiri", "Chikkamagaluru", 1400, "Easy", 3.0, "2 hrs", "Jun - Jan", "Waterfall trek through lush forest and coffee plantations."),
        t("Seethalayyanagiri", "Chikkamagaluru", 1913, "Easy", 1.5, "1 hr", "Sep - Feb", "Short climb near Mullayanagiri with a temple and great ridge views."),
        // Tumakuru
        t("Shivagange", "Tumakuru", 1368, "Moderate", 5.0, "3 hrs", "Oct - Feb", "Rocky hill with temples, caves and steep steps near Bengaluru."),
        t("Devarayanadurga", "Tumakuru", 1204, "Easy", 4.0, "2 hrs", "Oct - Feb", "Forest hill with ancient temples and fort ruins."),
        t("Siddara Betta", "Tumakuru", 1100, "Moderate", 6.0, "3 hrs", "Oct - Feb", "Granite monolith with caves and a rocky ascent."),
        t("Madhugiri", "Tumakuru", 1114, "Moderate", 4.0, "3 hrs", "Oct - Feb", "One of Asia's largest monolith hills with a historic fort."),
        t("Hutridurga", "Tumakuru", 1200, "Moderate", 5.0, "3 hrs", "Oct - Feb", "Fort hill trek with a rocky climb and open views."),
        // Chikkaballapur
        t("Nandi Hills", "Chikkaballapur", 1478, "Easy", 3.0, "2 hrs", "Oct - Feb", "Sunrise hill near Bengaluru with Tipu's Drop and gardens."),
        t("Skandagiri", "Chikkaballapur", 1350, "Moderate", 8.0, "4 hrs", "Oct - Feb", "Famous night trek to a sea-of-clouds sunrise view."),
        t("Avalabetta", "Chikkaballapur", 1450, "Easy", 4.0, "2 hrs", "Oct - Feb", "Peaceful hill with a temple and meadow views."),
        t("Brahmagiri Betta", "Chikkaballapur", 1200, "Moderate", 5.0, "3 hrs", "Oct - Feb", "Rugged boulder trail with views over the plains."),
        t("Channagiri Betta", "Chikkaballapur", 1100, "Easy", 3.0, "2 hrs", "Oct - Feb", "Short scenic hill trek popular with beginners."),
        // Shivamogga
        t("Kodachadri", "Shivamogga", 1343, "Moderate", 14.0, "6 hrs", "Oct - Feb", "Forested peak with a temple and grand Western Ghats views."),
        t("Kavaledurga", "Shivamogga", 850, "Moderate", 5.0, "3 hrs", "Oct - Feb", "Fort hill with temples and a dramatic ridge."),
        t("Kundadri", "Shivamogga", 826, "Easy", 3.0, "1.5 hrs", "Jun - Feb", "Monolith hill with a Jain temple and lovely monsoon views."),
        t("Narasimha Parvatha", "Shivamogga", 1200, "Moderate", 6.0, "3 hrs", "Oct - Feb", "Trail near Agumbe through rainforest to a viewpoint."),
        t("Barkana Falls Trail", "Shivamogga", 700, "Moderate", 5.0, "3 hrs", "Jun - Dec", "Forest trail near Agumbe to one of India's tallest waterfalls' viewpoint."),
        // Uttara Kannada
        t("Yana Rocks", "Uttara Kannada", 800, "Moderate", 4.0, "3 hrs", "Oct - Feb", "Towering black limestone rock formations in dense Sahyadri forest."),
        t("Devimane Ghat", "Uttara Kannada", 700, "Easy", 3.0, "2 hrs", "Oct - Feb", "Ghat trail with valley views and a hilltop temple."),
        t("Sathodi Falls Trail", "Uttara Kannada", 400, "Easy", 3.0, "2 hrs", "Jul - Dec", "Forest walk to a scenic waterfall, best in and after monsoon."),
        t("Unchalli Falls Trail", "Uttara Kannada", 500, "Moderate", 4.0, "3 hrs", "Jul - Dec", "Trek near Siddapur to a viewpoint over a deep gorge waterfall."),
        t("Kavala Caves Trail", "Uttara Kannada", 400, "Moderate", 4.0, "3 hrs", "Oct - Feb", "Trek to limestone caves with a Shiva lingam near Dandeli."),
        // Hassan
        t("Manjarabad Fort", "Hassan", 980, "Easy", 3.0, "2 hrs", "Oct - Feb", "Star-shaped Tipu Sultan fort near Sakleshpur with hill views."),
        t("Ettina Bhuja", "Hassan", 1200, "Moderate", 8.0, "4-5 hrs", "Oct - Feb", "Grassy ridge trek shaped like a bull's shoulder near Sakleshpur."),
        t("Jenukal Gudda", "Hassan", 1000, "Easy", 3.0, "2 hrs", "Oct - Feb", "Honey-rock hill with sunrise views over the Western Ghats."),
        t("Bisle Viewpoint", "Hassan", 900, "Easy", 3.0, "2 hrs", "Jun - Jan", "Forest trail near Bisle Ghat with a view of dense Sahyadri canopy."),
        t("Bettadabyraveshwara", "Hassan", 1000, "Easy", 3.0, "2 hrs", "Oct - Feb", "Short scenic hill climb topped with a shrine and open views.")
    )

    fun treksIn(district: String) = treks.filter { it.district == district }
    fun find(id: String) = treks.first { it.id == id }
}

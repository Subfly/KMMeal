import SwiftUI
import UIKit
import shared

@main
struct iOSApp: App {
    
    init() {
        DIHelper().doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			KMMealNavigationView()
		}
	}
}

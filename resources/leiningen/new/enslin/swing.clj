(ns {{name}}.base.swing
  (:require [{{name}}.base.engine :as engine]
            [{{name}}.settings :refer [debug defaults]]
            [seesaw.core :as s])
  (:import [org.pushingpixels.substance.api SubstanceLookAndFeel]
           [org.pushingpixels.substance.api.skin
            BusinessSkin BusinessBlueSteelSkin BusinessBlackSteelSkin
            CremeSkin CremeCoffeeSkin SaharaSkin ModerateSkin NebulaSkin
            NebulaBrickWallSkin AutumnSkin MistSilverSkin MistAquaSkin
            DustSkin DustCoffeeSkin GeminiSkin MarinerSkin
            OfficeSilver2007Skin OfficeBlue2007Skin OfficeBlack2007Skin
            TwilightSkin MagellanSkin GraphiteSkin GraphiteGlassSkin
            GraphiteAquaSkin RavenSkin ChallengerDeepSkin EmeraldDuskSkin]))

(defonce ^:private mainframe-atom (atom nil))

(defn ^:private center!
  [frame]
  (doto frame
    (.setLocationRelativeTo nil)))

(defn substance-theme!
  [k]
  (SubstanceLookAndFeel/setSkin
   (condp = k
     :business (BusinessSkin.)
     :business-blue-steel (BusinessBlueSteelSkin.)
     :business-black-steel (BusinessBlackSteelSkin.)
     :creme (CremeSkin.)
     :creme-coffee (CremeCoffeeSkin.)
     :sahara (SaharaSkin.)
     :moderate (ModerateSkin.)
     :nebula (NebulaSkin.)
     :nebula-brick-wall (NebulaBrickWallSkin.)
     :autumn (AutumnSkin.)
     :mist-silver (MistSilverSkin.)
     :mist-aqua (MistAquaSkin.)
     :dust (DustSkin.)
     :dust-coffee (DustCoffeeSkin.)
     :gemini (GeminiSkin.)
     :mariner (MarinerSkin.)
     :office-silver (OfficeSilver2007Skin.)
     :office-blue (OfficeBlue2007Skin.)
     :office-black (OfficeBlack2007Skin.)
     :twilight (TwilightSkin.)
     :magellan (MagellanSkin.)
     :graphite (GraphiteSkin.)
     :graphite-glass (GraphiteGlassSkin.)
     :graphite-aqua (GraphiteAquaSkin.)
     :raven (RavenSkin.)
     :challenger-deep (ChallengerDeepSkin.)
     :emerald-dusk (EmeraldDuskSkin.))))

(defn mainframe
  []
  @mainframe-atom)

(defn mainframe!
  [menu-coll]
  (reset! mainframe-atom
          (-> (s/frame
               :title (defaults :name)
               :icon (defaults :icon)
               :menubar (s/menubar :items menu-coll)
               :content (engine/web-panel "/")
               :size (defaults :frame-size)
               :on-close :exit)
              center! s/show!)))

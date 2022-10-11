package gallia
package pyviz

import enumeratum.{Enum, EnumEntry}
      
// ===========================================================================
sealed trait SeabornExampleDatasetName extends EnumEntry

  // see see https://github.com/mwaskom/seaborn-data (captured here as of 220331)
  // ---------------------------------------------------------------------------
  object SeabornExampleDatasetName extends Enum[SeabornExampleDatasetName] {  
    val values = findValues

    // ---------------------------------------------------------------------------
    case object anagrams        extends SeabornExampleDatasetName
    case object anscombe        extends SeabornExampleDatasetName
    case object attention       extends SeabornExampleDatasetName
    case object brain_networks  extends SeabornExampleDatasetName
      // transposed
        //node,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,2,2,3,3,1,1,2,2,3,3,1,1,1,1,1,1,1,1,2,2,3,1,1,2,2,3,4,1,1,1,1,1,1,2,2,3,3,4,4,1,1,2,2,3,3,4
        //hemi,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,lh,rh,lh,rh,rh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh,rh,lh
        //,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
        //0,56.05574417114258,92.03103637695312,3.391575574874878,38.65968322753906,26.203819274902344,-49.71556854248047,47.4610366821289,26.746612548828125,-35.898860931396484,-1.8891807794570925,5.898688316345215,-43.69232177734375,-47.66426467895508,12.2841215133667,1.5665380954742432,-13.042585372924805,-1.8552596569061282,-39.80590057373047,-30.831512451171875,-61.13700866699219,-25.82785606384277,39.02416229248047,-29.97164535522461,-6.1323723793029785,-56.75698852539063,0.2101360559463501,-33.01984405517578,2.9784927368164062,-8.327313423156737,15.077152252197266,-10.35627269744873,18.01881599426269,-63.29214477539063,-75.9951171875,-35.44591522216797,-99.3927230834961,-73.01741790771484,-18.968013763427734,14.880836486816404,-47.75459671020508,14.73847484588623,-16.853010177612305,-34.217819213867195,-66.33069610595703,-5.723308563232423,-32.08142852783203,-76.85454559326172,13.468185424804688,68.45629119873047,19.31100845336914,30.17892837524414,60.526405334472656,0.6079040169715881,-70.27054595947266,77.36577606201172,-21.73455047607422,1.0282527208328247,7.7917842864990225,68.90372467041016,-10.520872116088867,120.49046325683594,-39.686431884765625
        //1,55.5472526550293,43.6900749206543,-65.49598693847656,-13.974522590637207,-28.27496337890625,-39.05012893676758,-1.2106596231460571,-19.012897491455078,19.568010330200195,15.902982711791992,-23.231822967529297,-10.745866775512695,10.269545555114746,31.27583122253418,-26.30948829650879,-18.0770263671875,-10.259323120117188,-43.488677978515625,-63.96562957763672,47.78985595703125,-7.910674571990968,37.951271057128906,-9.677253723144533,-52.37350845336914,6.007475852966309,    
    case object car_crashes     extends SeabornExampleDatasetName
    case object diamonds        extends SeabornExampleDatasetName
    case object dots            extends SeabornExampleDatasetName
    case object exercise        extends SeabornExampleDatasetName
    case object flights         extends SeabornExampleDatasetName    
    case object fmri            extends SeabornExampleDatasetName
    case object gammas          extends SeabornExampleDatasetName
    case object geyser          extends SeabornExampleDatasetName
    case object iris            extends SeabornExampleDatasetName
    case object mpg             extends SeabornExampleDatasetName
    case object penguins        extends SeabornExampleDatasetName // contains nulls
    case object planets         extends SeabornExampleDatasetName
    case object taxis           extends SeabornExampleDatasetName // contains nulls
    case object tips            extends SeabornExampleDatasetName
    case object titanic         extends SeabornExampleDatasetName // contains nulls
  }  

// ===========================================================================

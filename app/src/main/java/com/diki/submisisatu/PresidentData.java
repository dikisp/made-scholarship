package com.diki.submisisatu;

import java.util.ArrayList;

public class PresidentData {
    public static  String[] [] data = new String[][]{
            {"Naruto Uzumaki", "Hokage Ketujuh (Nanadaime Hokage)", "https://3.bp.blogspot.com/-OADgnU4qfDY/WobFDdmOVGI/AAAAAAAARQk/EWcqNiIRUnIfS__SVzixDsBFqMF_eEEhACLcBGAs/s320/hokage%2Bkonoha%2Bterkuat%2Bnaruto%2Buzumaki.jpg","Siapa lagi hokage Konoha terkuat kalau bukan sang tokoh utama yaitu Naruto Uzumaki. Tidak hanya menjadi hokage terkuat, Naruto juga menjadi karakter terkuat dalam keseluruhan serial cerita Naruto. Naruto adalah Hokage Ketujuh menggantikan posisi Kakashi Hatake. Menjadi hokage adalah cita-cita Naruto sejak kecil."},
            {"Hashirama Senju", "Hokage Pertama (Shodaime Hokage)", "https://3.bp.blogspot.com/-zE-DU6sonDk/WobE-UoHrpI/AAAAAAAARQc/1rLoFlBJJEAwIbJG0T9Rb7HxnFoILhdrgCLcBGAs/s400/hokage%2Bkonoha%2Bterkuat%2Bhashirama%2Bsenju.jpg", "Hashirama bahkan dijuluki sebagai ninja legendaris karena saat bertarung hampir tak terkalahkan. Ia memiliki jurus elemen kayu yang menjadi ciri khasnya dan tidak dikuasai oleh shinobi lain. Hashirama juga menguasai Sage Mode sehingga ia bisa melakukan regenerasi terus menerus pada tubuhnya."},
            {"Tobirama Senju", "Hokage Kedua (Nidaime Hokage)", "https://2.bp.blogspot.com/-hq5guKdHHu4/WobFNOtt7xI/AAAAAAAARQo/BPBaJ6N-ddMVRFYTQ6bjef8T7M3CI2yagCLcBGAs/s320/hokage%2Bkonoha%2Bterkuat%2Btobirama%2Bsenju.png", "Tobirama Senju adalah Hokage Kedua sekaligus adik dari Hokage Pertama, Hashirama Senju. Tobirama meneruskan kepemimpinan Hashirama. Ia memiliki sikap yang lebih tegas, dingin dan disiplin dibandingkan kakaknya. Tobirama berjasa membangun Desa Konoha dengan membentuk akademi dan institusi kepolisian Konoha."},
            {"Minato Namikaze", "Hokage Keempat (Yondaime Hokage)", "https://4.bp.blogspot.com/-xGv1uhIxDN8/WobEuCqeytI/AAAAAAAARQY/awQT6r60THc82ulkYt_nPIdHwayxxEf2QCLcBGAs/s400/hokage%2Bkonoha%2Bterkuat%2Bminato%2Bnamikaze.jpg", "osisi keempat ada Hokage Keempat, Minato Namikaze. Ia adalah ayah dari Naruto yang kemudian menjadi Hokage Ketujuh. Minato hanya menjabat sebagai Hokage dalam waktu singkat. Ia meninggal saat mengorbankan diri menyegel rubah ekor sembilan. Posisi Hokage setelah ia meninggal kemudian diisi kembali oleh Hokage Ketiga"},
            {"Hiruzen Sarutobi", "Hokage Ketiga (Sandaime Hokage)", "https://4.bp.blogspot.com/-Zatu55vovTM/WobEgW8NAqI/AAAAAAAARQU/3xiJPDhCzgAncZ7kbrlzvz8wpykR6zt3ACLcBGAs/s320/hokage%2Bkonoha%2Bterkuat%2Bhiruzen%2Bsarutobi.jpg","Hiruzen Sarutobi adalah Hokage Ketiga dan menjadi salah satu tokoh Naruto terkuat. Ia merupakan ninja jenius karena bisa menguasai banyak jurus dan teknik ninja di Konoha sehingga dijuluki profesor shinobi. Kemampuannya sangat hebat didukung dengan kepemimpinan yang tegas dan bijaksana"},
            {"Kakashi Hatake", "Hokage Keenam (Rokudaime Hokage)", "https://2.bp.blogspot.com/-6ugKOc6VAXA/WobDv8EuUvI/AAAAAAAARQI/lTFWbK3UkI0PeZMQuesK9NrK6b79m1K_wCLcBGAs/s400/hokage%2Bkonoha%2Bterkuat%2Bkakashi%2Bhatake.png", "Kakashi Hatake merupakan Hokage Keenam di Konoha menggantikan posisi Tsunade. Kakashi adalah murid dari Hokage Keempat, Minato Namikaze dan memiliki 3 murid kuat, Naruto, Sasuke dan Sakura. Naruto bahkan menjadi Hokage Ketujuh menggantikan Kakashi. Kakashi terkenal sebagai ninja berbakat yang dijuluki Copy Ninja"},
            {"Tsunade", "Hokage Kelima (Godaime Hokage)", "https://3.bp.blogspot.com/-_rXIz99sfPI/WobDpEJ8YdI/AAAAAAAARQE/kDgO91L9WxsUK5f292WxM3aCetQyaoCtQCLcBGAs/s320/hokage%2Bkonoha%2Bterkuat%2Btsunade.jpg","Tsunade menjadi satu-satunya hokage wanita dalam sejarah Konoha. Tsunade memiliki kemampuan hebat sebagai ninja medis, hingga dijuluki ninja medis terhebat. Kekuatan bertarungnya juga sangat mengerikan baik lewat pukulan atau tendangan."}
    };


    public static ArrayList<President> getListData(){
      President president = null;
      ArrayList<President> list = new ArrayList<>();
      for (String[] aData : data){
          president = new President();
          president.setName(aData[0]);
          president.setRemarks(aData[1]);
          president.setPhoto(aData[2]);

          list.add(president);
      }
      return list;
    }
}

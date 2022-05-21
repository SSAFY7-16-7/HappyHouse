<template>
  <div>
    <div id="map" @click="initComponent"></div>
    <div class="button-group"></div>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  data: () => {
    return {
      map: null, //맵을 전역변수로
      hospitalLoc: [], //병원 데이터를 10개씩(페이징 단위) 저장
      pageLimit: 10, //페이징 단위
      markers: [], //현재 맵에 띄울 마커 10개를 전역변수
      infowindows: [], //맵에 마커에 마우스를 올렸을시 뜨는 인포윈도우 10개 전역변수
    };
  },
  watch: {
    "$route.query": function () {
      this.initComponent();
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initComponent();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initComponent());
    }

    this.$store.subscribe((mutation, state) => {
      if (mutation.type == "addhospitalZoom") {
        var zoomer = this.$store.getters.hospitalZoom;
        this.map.setLevel(3);
        this.map.setCenter(new kakao.maps.LatLng(zoomer.lng, zoomer.lat));
      } else if (mutation.type == "addHospitalHover") {
        var hover_hos = this.$store.getters.hospitalHover;
        var overlay = new kakao.maps.LatLng(hover_hos.lng, hover_hos.lat);
        // this.map.setLevel(3); //마커 눌렀을때
        if (this.map != null) this.map.setCenter(overlay);
        for (var i = 0; i < this.markers.length; i++) {
          var marker = this.markers[i];
          if (hover_hos.name == marker.getTitle()) {
            this.infowindows[i].open(this.map, marker);
          } else {
            this.infowindows[i].close();
          }
        }
      }
    });
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
    initComponent() {
      axios
        .get(
          "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?LAWD_CD=11110&DEAL_YMD=201512&serviceKey=%2BERelxkpcuje7NJLgLdEDeimdjhWgfOhmG3W7Fz9tWihQjyffyBvz1pbHDk2Wi5hUkv9DDYDlrxwGEm887GYLw%3D%3D"
          // {
          //   params: {
          //     limit: this.pageLimit,
          //     offset: `${this.$route.query.no - this.pageLimit}`,
          //     word: this.$route.query.word,
          //     subject: "",
          //     sido: "",
          //     gu: "",
          //   },
          // }
        )
        .then((response) => {
          this.hospitalLoc = response.data;
          setTimeout(() => {
            this.initMap();
          }, 600);
        })
        .catch((error) => {});
    },
    initMap() {
      //마커 이미지!
      var imageSrc = require("@/assets/img/marker.png");
      var imageSize = new kakao.maps.Size(25, 37);
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
      var multi = new kakao.maps.LatLng(37.5012743, 127.039585);
      var map = new kakao.maps.Map(document.getElementById("map"), {
        center: multi, // 지도의 중심좌표
        level: 4, // 지도의 확대 레벨
      });
      var markers = [];
      var infowindows = [];
      var centerLat = 0;
      for (var i = 0; i < this.hospitalLoc.length; i++) {
        var info = this.hospitalLoc[i];
        centerLng += info.lng;
        centerLat += info.lat;
        var marker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(info.lng, info.lat),
          title: info.name,
          image: markerImage,
          clickable: true, //마커 클릭시 지도 동작x
        });
        var iwContent =
            '<div style="text-align:center; width: 150px; overflow:auto; margin-right : -145px; ">' +
            '<div style="color: #17a2b8; font-size:1.1em;">' +
            info.name +
            "</div>" +
            '<span style="font-size:0.9em; margin-top : -3em">' +
            info.gu +
            "</span>" +
            "<br></div>",
          iwRemoveable = false;
        var infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
        });
        infowindows.push(infowindow);
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.iwOpen(map, marker, infowindow, infowindows)
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.iwClose(infowindow)
        );
        kakao.maps.event.addListener(
          marker,
          "click",
          this.markerClick(marker, map)
        );
        markers.push(marker);
      }
      map.setCenter(
        new kakao.maps.LatLng(
          centerLng / this.hospitalLoc.length,
          centerLat / this.hospitalLoc.length
        )
      );
      // 마커 클러스터러를 생성합니다
      var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 8, // 클러스터 할 최소 지도 레벨
        calculator: [3, 5, 10], // 클러스터의 크기 구분 값, 각 사이값마다 설정된 text나 style이 적용된다
        disableClickZoom: true,
      });
      kakao.maps.event.addListener(
        clusterer,
        "clusterclick",
        function (cluster) {
          var level = map.getLevel();
          if (level <= 2) {
            map.setLevel(1);
            map.setCenter(cluster.getCenter());
          } else {
            // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다f
            map.setLevel(level - 2, { anchor: cluster.getCenter() });
            map.setCenter(cluster.getCenter());
          }
        }
      );
      clusterer.addMarkers(markers);
      if (clusterer._clusters.length == 1) {
        map.setLevel(7); //처음 지도 거리
      }
      this.infowindows = infowindows;
      this.markers = markers;
      this.map = map;
    },
    iwOpen(map, marker, infowindow, infowindows) {
      return function () {
        this.infowindows = infowindows;
        for (var i = 0; i < this.infowindows.length; i++) {
          this.infowindows[i].close();
        }
        infowindow.open(map, marker);
      };
    },
    iwClose(infowindow) {
      return function () {
        infowindow.close();
      };
    },
    markerClick(marker, map) {
      return function () {
        map.setLevel(3);
        map.setCenter(marker.getPosition());
      };
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 1688px;
  height: 783px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>

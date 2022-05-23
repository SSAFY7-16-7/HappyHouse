<template>
  <div>
    <div id="map"></div>
    <div class="button-group">
      <button @click="displayMarker(markerPositions1)">marker set 1</button>
      <button @click="displayMarker([])">marker set 3 (empty)</button>
      <button @click="displayInfoWindow">infowindow</button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { BUS } from "@/store/modules/EventBus";

const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      bounds: {
        qa: null,
        pa: null,
        ha: null,
        oa: null,
      },
      markers: [],
      infowindow: null,
      place: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["house", "markerPositions"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* globalkakao */
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0&libraries=services";
      document.head.appendChild(script);
      script.onload = () => kakao.maps.load(this.initMap);
    }

    BUS.$on(
      "moveDong",
      function (dong) {
        console.log("moveDong", this.place);
        this.place.keywordSearch(dong, this.placesSearchCB);
      }.bind(this)
    );
  },
  methods: {
    ...mapActions(houseStore, ["addressHouse"]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5283169, 126.9294254),
        level: 3,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      // 장소 검색 객체를 생성합니다
      const ps = new kakao.maps.services.Places();
      this.place = ps;

      const base = this;
      kakao.maps.event.addListener(this.map, "dragend", function () {
        base.getBounds();
      });

      kakao.maps.event.addListener(this.map, "zoom_changed", function () {
        base.getBounds();
      });
    },
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        const bounds = new kakao.maps.LatLngBounds();

        for (var i = 0; i < data.length; i++) {
          bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        this.map.setBounds(bounds);
        this.getBounds();
      }
    },
    getBounds() {
      console.log("getBounds");
      // 지도 영역정보를 얻어옵니다
      const bounds = this.map.getBounds();

      this.bounds.qa = bounds.qa;
      this.bounds.pa = bounds.pa;
      this.bounds.ha = bounds.ha;
      this.bounds.oa = bounds.oa;

      this.addressHouse(this.bounds);
      this.displayMarker(this.markerPositions);
    },
    displayMarker(markerPositions) {
      console.log("displayMarker");
      const areas = new kakao.maps.LatLngBounds();

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      console.log("markers : ", this.markers);
      console.log("positions : ", positions);

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        // this.map.setBounds(bounds);
      }
    },
    // displayMarker(markerPositions) {
    //   const areas = new kakao.maps.LatLngBounds();

    //   if (this.markers.length > 0) {
    //     this.markers.forEach((marker) => marker.setMap(null));
    //   }

    //   const positions = markerPositions.map(
    //     (position) => new kakao.maps.LatLng(...position)
    //   );

    //   if (positions.length > 0) {
    //     this.markers = positions.map(
    //       (position) =>
    //         new kakao.maps.Marker({
    //           map: this.map,
    //           position,
    //         })
    //     );

    //     const bounds = positions.reduce(
    //       (bounds, latlng) => bounds.extend(latlng),
    //       new kakao.maps.LatLngBounds()
    //     );

    //     // this.map.setBounds(bounds);
    //   }
    // },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100vw;
  height: 100vh;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>

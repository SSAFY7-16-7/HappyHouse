<template>
  <div>
    <div id="map"></div>
    <div class="button-group">
      <button @click="changeSize(400)">show</button>
      <button @click="displayMarker(markerPositions1)">marker set 1</button>
      <button @click="displayMarker(markerPositions2)">marker set 2</button>
      <button @click="displayMarker([])">marker set 3 (empty)</button>
      <button @click="displayInfoWindow">infowindow</button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { BUS } from "@/store/modules/EventBus";
export default {
  name: "KakaoMap",
  data() {
    return {
      markerPositions1: [
        [33.452278, 126.567803],
        [33.452671, 126.574792],
        [33.451744, 126.572441],
      ],
      markerPositions2: [
        [37.499590490909185, 127.0263723554437],
        [37.499427948430814, 127.02794423197847],
        [37.498553760499505, 127.02882598822454],
        [37.497625593121384, 127.02935713582038],
        [37.49629291770947, 127.02587362608637],
        [37.49754540521486, 127.02546694890695],
        [37.49646391248451, 127.02675574250912],
      ],
      markers: [],

      infowindow: null,
      position: {
        lat: "",
        lng: "",
      },
      itemMarker: "",
      map: "",
      test: "test!!!",
      center: "",
    };
  },
  computed: {
    ...mapState("houseStore", [""]),
  },
  created() {
    BUS.$on("change-postion", (position) => {
      //console.log("아이템 클릭 - 변경할 좌표:", position);
      this.position = position;
      this.changeItem(position);
    });
    BUS.$on("init-position", (position) => {
      //console.log("관심리스트 오픈 - 초기 좌표:", position);
      this.position = position;
    });
  },
  mounted() {
    const script = document.createElement("script");
    /* global kakao */
    script.onload = () => kakao.maps.load(this.initMap);
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ff39ee8d511b533648d3baac6dd7efff&libraries=services";
    document.head.appendChild(script);
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const adjLng = -0.018 + parseFloat(this.position.lng);
      //console.log("조정 lng", adjLng);
      const options = {
        center: new kakao.maps.LatLng(this.position.lat, adjLng),
        level: 5,
      };

      this.map = new kakao.maps.Map(container, options);
      const marker = new kakao.maps.LatLng(
        this.position.lat,
        this.position.lng
      );
      this.itemMarker = new kakao.maps.Marker({
        position: marker,
      });

      this.itemMarker.setMap(this.map);
    },
    changeItem(position) {
      // 지도 중심 이동
      const adjLng = parseFloat(this.position.lng) - 0.02; // 센터 경도 조정
      this.center = new kakao.maps.LatLng(position.lat, adjLng);
      this.marker = new kakao.maps.LatLng(position.lat, this.position.lng);
      this.map.panTo(this.center);

      // 마커 변경
      this.itemMarker.setMap(null); // 기존의 설정된 마커 제거
      this.itemMarker = new kakao.maps.Marker({
        position: this.marker,
      });
      this.itemMarker.setMap(this.map); // 현재 클릭한 아이템의 위치로 마커 등록
    },

    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

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

        this.map.setBounds(bounds);
      }
    },
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

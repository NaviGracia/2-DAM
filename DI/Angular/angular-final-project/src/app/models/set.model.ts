export interface PokemonSet {
  id: string;
  name: string;
  releaseDate: string;
  images: {
    logo: string;
    symbol: string;
  };
}

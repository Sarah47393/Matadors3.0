<?php

namespace App\Entity;

use App\Repository\VisiteRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=VisiteRepository::class)
 */
class Visite
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="date")
     *@Assert\NotBlank(message="vérifier la date")
     */
    private $date;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="vérifier les infos")
     */
    private $description;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="vérifier la disponibilité")
     */
    private $disponibilite;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="vérifier votre num_carte")
     */
    private $num_carte;

    /**
     * @ORM\Column(type="decimal", precision=10, scale=0)
     * @Assert\NotBlank(message="vérifier le montant")
     */
    private $montant;

    /**
     * @ORM\OneToMany(targetEntity=Reservation::class, mappedBy="visite", orphanRemoval=true)
     */
    private $reservation;

    /**
     * @ORM\OneToOne(targetEntity=Evenement::class, inversedBy="visite", cascade={"persist", "remove"})
     */
    private $evenement;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $sexe;

    

    public function __construct()
    {
        $this->reservation = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getDisponibilite(): ?bool
    {
        return $this->disponibilite;
    }

    public function setDisponibilite(bool $disponibilite): self
    {
        $this->disponibilite = $disponibilite;

        return $this;
    }

    public function getNumCarte(): ?int
    {
        return $this->num_carte;
    }

    public function setNumCarte(int $num_carte): self
    {
        $this->num_carte = $num_carte;

        return $this;
    }

    public function getMontant(): ?string
    {
        return $this->montant;
    }

    public function setMontant(string $montant): self
    {
        $this->montant = $montant;

        return $this;
    }

    /**
     * @return Collection|Reservation[]
     */
    public function getReservation(): Collection
    {
        return $this->reservation;
    }

    public function addVisite(Reservation $visite): self
    {
        if (!$this->visite->contains($visite)) {
            $this->visite[] = $visite;
            $visite->setVisite($this);
        }

        return $this;
    }

    public function removeVisite(Reservation $visite): self
    {
        if ($this->visite->removeElement($visite)) {
            // set the owning side to null (unless already changed)
            if ($visite->getVisite() === $this) {
                $visite->setVisite(null);
            }
        }
        return $this;
    }

    public function __toString(){
        return $this->description;
        }

    public function getEvenement(): ?Evenement
    {
        return $this->evenement;
    }

    public function setEvenement(?Evenement $evenement): self
    {
        $this->evenement = $evenement;

        return $this;
    }

    public function getSexe(): ?string
    {
        return $this->sexe;
    }

    public function setSexe(string $sexe): self
    {
        $this->sexe = $sexe;

        return $this;
    }

    
}
